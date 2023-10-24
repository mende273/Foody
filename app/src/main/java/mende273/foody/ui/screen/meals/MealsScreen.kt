package mende273.foody.ui.screen.meals

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import mende273.foody.R
import mende273.foody.domain.Tab
import mende273.foody.domain.model.Meals
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.LargeText
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.component.ScrollableTabRowComponent
import mende273.foody.ui.component.SmallButton
import mende273.foody.ui.state.UIState
import mende273.foody.util.getGridCellsCount

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    viewModel: MealsViewModel,
    windowSize: WindowSizeClass,
    onMealClicked: (String) -> Unit
) {
    val uiState by viewModel.uiStateCategories.collectAsStateWithLifecycle()

    val uiStateCategoryMeals by viewModel.uiStateCategoryMeals.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    var shouldShowFilterDialog by remember {
        mutableStateOf(false)
    }

    if (shouldShowFilterDialog) {
        FilterDialog(onDismissRequest = {
            shouldShowFilterDialog = false
        })
    }

    Column(modifier) {
        when (uiState) {
            is UIState.Error -> ErrorComponent(
                modifier = Modifier
                    .fillMaxSize(),
                text = (uiState as UIState.Error).errorMessage
            )

            is UIState.Loading -> ProgressBar(Modifier.fillMaxWidth())

            is UIState.Success -> {
                val pagerState = rememberPagerState(
                    pageCount = {
                        (uiState as UIState.Success<List<String>>)
                            .data.size
                    }
                )

                LaunchedEffect(key1 = pagerState.currentPage, block = {
                    val category = (uiState as UIState.Success<List<String>>)
                        .data[pagerState.currentPage]
                    viewModel.loadCategoryData(category)
                })

                MealsHeaderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.normal_padding)),
                    title = "Categories",
                    onFilterClicked = {
                        shouldShowFilterDialog = true
                    }
                )

                ScrollableTabRowComponent(
                    items = (uiState as UIState.Success<List<String>>).data
                        .map { Tab(it) }.toTypedArray(),
                    pagerState = pagerState,
                    tabEvent = { index -> coroutineScope.launch { pagerState.scrollToPage(index) } }
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_padding)))

                HorizontalPager(
                    state = pagerState,
                    pageSpacing = 0.dp,
                    userScrollEnabled = true,
                    beyondBoundsPageCount = 0,
                    pageContent = {
                        MealsGridSection(
                            uiState = uiStateCategoryMeals,
                            gridCellsCount = windowSize.getGridCellsCount(),
                            onMealClicked = { onMealClicked(it) }
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun MealsHeaderSection(
    modifier: Modifier = Modifier,
    title: String,
    onFilterClicked: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        LargeText(
            modifier = Modifier.align(Alignment.CenterStart),
            text = title
        )

        SmallButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = "Filter",
            onClicked = { onFilterClicked() }
        )
    }
}

@Composable
private fun MealsGridSection(
    modifier: Modifier = Modifier,
    uiState: UIState<Meals>,
    gridCellsCount: Int,
    onMealClicked: (String) -> Unit
) {
    when (uiState) {
        is UIState.Error -> ErrorComponent(
            modifier = modifier,
            text = uiState.errorMessage
        )

        is UIState.Loading -> ProgressBar(modifier)
        is UIState.Success -> {
            MealsGrid(
                gridCellsCount = gridCellsCount,
                meals = uiState.data.meals,
                onMealClicked = {
                    onMealClicked(it)
                }
            )
        }
    }
}

@Composable
private fun FilterDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "This is a test dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}
