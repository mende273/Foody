package mende273.foody.ui.screen.meals

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
import mende273.foody.ui.component.MediumText
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.component.ScrollableTabRowComponent
import mende273.foody.ui.component.SmallButton
import mende273.foody.ui.state.Filter
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
    val uiStateCurrentFilter by viewModel.currentFilter.collectAsStateWithLifecycle()

    val uiStateCurrentFilterOption by viewModel.meals.collectAsStateWithLifecycle()

    val headerTitle by viewModel.headerTitle.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    var shouldShowFilterDialog by remember {
        mutableStateOf(false)
    }

    var shouldMoveToFirstPage by remember {
        mutableStateOf(false)
    }

    if (shouldShowFilterDialog) {
        FilterDialog(
            currentFilter = headerTitle,
            availableFilters = viewModel.getAvailableFilters(),
            onDismissRequest = {
                shouldShowFilterDialog = false
            },
            onFilterClicked = {
                shouldShowFilterDialog = false
                viewModel.loadFilter(it)
                shouldMoveToFirstPage = true
            }
        )
    }

    Column(modifier.testTag("test_tag_meals_screen")) {
        when (uiStateCurrentFilter) {
            is UIState.Success -> {
                val pagerState = rememberPagerState(
                    pageCount = {
                        (uiStateCurrentFilter as UIState.Success<List<String>>)
                            .data.size
                    }
                )

                if (shouldMoveToFirstPage) {
                    LaunchedEffect(key1 = pagerState.currentPage, block = {
                        pagerState.scrollToPage(0)
                    })
                    shouldMoveToFirstPage = false
                }

                LaunchedEffect(key1 = headerTitle.title + pagerState.currentPage, block = {
                    val category =
                        (uiStateCurrentFilter as UIState.Success<List<String>>)
                            .data[pagerState.currentPage]
                    viewModel.fetchMeals(category)
                })

                HeaderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.normal_padding)),
                    title = stringResource(id = headerTitle.title),
                    onFilterClicked = { shouldShowFilterDialog = true }
                )

                PagerSection(
                    currentFilterData =
                    (uiStateCurrentFilter as UIState.Success<List<String>>)
                        .data
                        .map { Tab(it) }.toTypedArray(),
                    uiStateFilterOptionData = uiStateCurrentFilterOption,
                    pagerState = pagerState,
                    windowSize = windowSize,
                    onMealClicked = { onMealClicked(it) },
                    scrollToPage = { index ->
                        coroutineScope.launch { pagerState.scrollToPage(index) }
                    }
                )
            }

            is UIState.Error -> ErrorComponent(
                modifier = Modifier.fillMaxSize(),
                text = (uiStateCurrentFilter as UIState.Error).errorMessage
            )

            UIState.Loading -> ProgressBar(Modifier.fillMaxSize())
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerSection(
    currentFilterData: Array<Tab>,
    uiStateFilterOptionData: UIState<Meals>,
    pagerState: PagerState,
    windowSize: WindowSizeClass,
    onMealClicked: (String) -> Unit,
    scrollToPage: (Int) -> Unit
) {
    ScrollableTabRowComponent(
        items = currentFilterData,
        pagerState = pagerState,
        tabEvent = { index -> scrollToPage(index) }
    )

    Spacer(
        modifier = Modifier.height(
            dimensionResource(id = R.dimen.normal_padding)
        )
    )

    HorizontalPager(
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = false,
        beyondBoundsPageCount = 0,
        pageContent = {
            GridSection(
                modifier = Modifier.fillMaxSize(),
                uiState = uiStateFilterOptionData,
                gridCellsCount = windowSize.getGridCellsCount(),
                onMealClicked = { onMealClicked(it) }
            )
        }
    )
}

@Composable
private fun HeaderSection(
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
            text = stringResource(id = R.string.filter),
            onClicked = { onFilterClicked() }
        )
    }
}

@Composable
private fun GridSection(
    modifier: Modifier = Modifier,
    uiState: UIState<Meals>,
    gridCellsCount: Int,
    onMealClicked: (String) -> Unit
) {
    when (uiState) {
        is UIState.Error -> ErrorComponent(
            modifier = Modifier.fillMaxSize(),
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
private fun FilterDialog(
    currentFilter: Filter,
    availableFilters: List<Filter>,
    onDismissRequest: () -> Unit,
    onFilterClicked: (Filter) -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 250.dp),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.normal_padding)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.large_padding)),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.normal_padding)
                )
            ) {
                availableFilters.forEach { filter ->
                    FilterDialogItem(
                        currentFilter = currentFilter,
                        filter = filter,
                        onFilterClicked = {
                            onFilterClicked(filter)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterDialogItem(
    currentFilter: Filter,
    filter: Filter,
    onFilterClicked: (Filter) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = currentFilter.title == filter.title,
                onClick = {
                    onFilterClicked(filter)
                }
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.primary
            ),
            selected = currentFilter.title == filter.title,
            onClick = { onFilterClicked(filter) }
        )
        MediumText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = filter.title)
        )
    }
}
