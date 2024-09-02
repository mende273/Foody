package mende273.foody.feature.filtermeals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.common.state.UIState
import mende273.foody.common.ui.component.TopBar
import mende273.foody.common.ui.component.mealsgrid.MealsGrid
import mende273.foody.common.ui.component.uistatewrapper.UiStateWrapper
import mende273.foody.common.ui.getGridCellsCount
import mende273.foody.common.ui.preview.model.MealsUiStatePreviewModel
import mende273.foody.common.ui.preview.parameter.MealsUiStateParameterPreview
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.core.domain.model.Meal

@Composable
fun FilterMealsScreen(
    modifier: Modifier = Modifier,
    viewModel: FilterMealsViewModel,
    windowSize: WindowSizeClass,
    name: String,
    filterType: FilterType,
    onMealClicked: (Long) -> Unit,
    onNavigateBackClicked: () -> Unit
) {
    val headerTitle by viewModel.headerTitle.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = name, block = {
        viewModel.requestData(name, filterType)
    })

    ScreenContents(
        modifier = modifier,
        headerTitle = headerTitle?.let { stringResource(id = it, name) } ?: name,
        uiState = uiState,
        windowSize = windowSize,
        onNavigateBackClicked = { onNavigateBackClicked() },
        onMealClicked = onMealClicked
    )
}

@Composable
private fun ScreenContents(
    modifier: Modifier,
    headerTitle: String,
    uiState: UIState<List<Meal>>,
    windowSize: WindowSizeClass,
    onNavigateBackClicked: () -> Unit,
    onMealClicked: (Long) -> Unit
) {
    Column(modifier = modifier) {
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = headerTitle,
            isBackButtonEnabled = true,
            onNavigateBackClicked = { onNavigateBackClicked() }
        )

        UiStateWrapper(uiState = uiState) { meals ->
            MealsGrid(
                gridCellsCount = windowSize.getGridCellsCount(),
                meals = meals,
                onMealClicked = onMealClicked
            )
        }
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun ScreenPreview(
    @PreviewParameter(MealsUiStateParameterPreview::class) previewModel: MealsUiStatePreviewModel
) {
    FoodyTheme {
        ScreenContents(
            modifier = Modifier.fillMaxSize(),
            headerTitle = "Filter meals",
            uiState = previewModel.uiState,
            windowSize = previewModel.windowSize,
            onNavigateBackClicked = {},
            onMealClicked = {}
        )
    }
}
