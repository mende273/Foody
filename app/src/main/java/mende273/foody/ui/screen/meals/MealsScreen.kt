package mende273.foody.ui.screen.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mende273.foody.R
import mende273.foody.domain.model.Meal
import mende273.foody.ui.common.model.Tab
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.NormalText
import mende273.foody.ui.component.ScrollableTabRowComponent
import mende273.foody.ui.component.SmallButton
import mende273.foody.ui.component.UiStateWrapper
import mende273.foody.ui.preview.model.MealsScreenPreviewModel
import mende273.foody.ui.preview.parameter.MealsScreenParameterProvider
import mende273.foody.ui.screen.meals.dialog.FilterDialog
import mende273.foody.ui.state.Filter
import mende273.foody.ui.state.UIState
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.largeTextStyle
import mende273.foody.ui.theme.spacing
import mende273.foody.util.getGridCellsCount

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    viewModel: MealsViewModel,
    windowSize: WindowSizeClass,
    onMealClicked: (Long) -> Unit
) {
    val uiStateCurrentFilterTabs by
    viewModel.uiStateCurrentFilterTabs.collectAsStateWithLifecycle()

    val uiStateCurrentFilterTabItems by
    viewModel.uiStateCurrentFilterTabItems.collectAsStateWithLifecycle()

    val currentFilter by viewModel.currentFilter.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    var shouldShowFilterDialog by remember {
        mutableStateOf(false)
    }

    var shouldMoveToFirstPage by remember {
        mutableStateOf(false)
    }

    if (shouldShowFilterDialog) {
        FilterDialog(
            currentFilter = currentFilter,
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

    ScreenContents(
        modifier = modifier,
        uiStateCurrentFilterTabs = uiStateCurrentFilterTabs,
        uiStateCurrentFilterTabItems = uiStateCurrentFilterTabItems,
        currentFilter = currentFilter,
        shouldMoveToFirstPage = shouldMoveToFirstPage,
        windowSize = windowSize,
        coroutineScope = coroutineScope,
        onShouldMoveToFirstPage = { shouldMoveToFirstPage = it },
        onFetchMeals = { viewModel.fetchMeals(it) },
        onShouldShowFilterDialog = { shouldShowFilterDialog = it },
        onMealClicked = onMealClicked
    )
}

@Composable
private fun ScreenContents(
    modifier: Modifier,
    uiStateCurrentFilterTabs: UIState<List<String>>,
    uiStateCurrentFilterTabItems: UIState<List<Meal>>,
    currentFilter: Filter,
    shouldMoveToFirstPage: Boolean,
    windowSize: WindowSizeClass,
    coroutineScope: CoroutineScope,
    onShouldMoveToFirstPage: (Boolean) -> Unit,
    onFetchMeals: (String) -> Unit,
    onShouldShowFilterDialog: (Boolean) -> Unit,
    onMealClicked: (Long) -> Unit
) {
    Column(modifier.testTag("test_tag_meals_screen")) {
        UiStateWrapper(uiState = uiStateCurrentFilterTabs) { tabs ->
            val pagerState = rememberPagerState(
                pageCount = { tabs.size }
            )

            if (shouldMoveToFirstPage) {
                LaunchedEffect(key1 = pagerState.currentPage, block = {
                    pagerState.scrollToPage(0)
                })
                onShouldMoveToFirstPage(false)
            }

            LaunchedEffect(key1 = currentFilter.title + pagerState.currentPage, block = {
                onFetchMeals(tabs[pagerState.currentPage])
            })

            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.normal),
                title = stringResource(id = currentFilter.title),
                onFilterClicked = { onShouldShowFilterDialog(true) }
            )

            PagerSection(
                currentFilterData =
                tabs.map { Tab(it) }.toTypedArray(),
                uiStateFilterOptionData = uiStateCurrentFilterTabItems,
                pagerState = pagerState,
                windowSize = windowSize,
                onMealClicked = onMealClicked,
                scrollToPage = { index ->
                    coroutineScope.launch { pagerState.scrollToPage(index) }
                }
            )
        }
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun MealsScreenContentsPreview(
    @PreviewParameter(MealsScreenParameterProvider::class) previewModel: MealsScreenPreviewModel
) {
    FoodyTheme {
        ScreenContents(
            modifier = Modifier.fillMaxSize(),
            uiStateCurrentFilterTabs = previewModel.currentFilterTabsUiState,
            uiStateCurrentFilterTabItems = previewModel.currentFilterTabItemsUiState,
            currentFilter = previewModel.currentFilter,
            shouldMoveToFirstPage = false,
            windowSize = previewModel.windowSize,
            coroutineScope = rememberCoroutineScope(),
            onShouldMoveToFirstPage = {},
            onFetchMeals = {},
            onShouldShowFilterDialog = {},
            onMealClicked = {}
        )
    }
}

@Composable
private fun PagerSection(
    currentFilterData: Array<Tab>,
    uiStateFilterOptionData: UIState<List<Meal>>,
    pagerState: PagerState,
    windowSize: WindowSizeClass,
    onMealClicked: (Long) -> Unit,
    scrollToPage: (Int) -> Unit
) {
    ScrollableTabRowComponent(
        items = currentFilterData,
        pagerState = pagerState,
        tabEvent = { index -> scrollToPage(index) }
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

    HorizontalPager(
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = false,
        pageContent = {
            UiStateWrapper(uiState = uiStateFilterOptionData) { meals ->
                MealsGrid(
                    gridCellsCount = windowSize.getGridCellsCount(),
                    meals = meals,
                    onMealClicked = onMealClicked
                )
            }
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
        NormalText(
            modifier = Modifier.align(Alignment.CenterStart),
            text = title,
            textStyle = largeTextStyle()
        )

        SmallButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = stringResource(id = R.string.filter),
            onClicked = { onFilterClicked() }
        )
    }
}

@PreviewLightDark
@Composable
private fun HeaderSectionPreview() {
    FoodyTheme {
        HeaderSection(
            Modifier.fillMaxWidth(),
            title = "Category",
            onFilterClicked = {}
        )
    }
}
