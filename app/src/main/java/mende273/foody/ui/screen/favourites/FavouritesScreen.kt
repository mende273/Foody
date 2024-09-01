package mende273.foody.ui.screen.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.core.domain.model.Meal
import mende273.foody.ui.common.getGridCellsCount
import mende273.foody.ui.common.getTopPadding
import mende273.foody.ui.common.preview.model.MealsUiStatePreviewModel
import mende273.foody.ui.common.preview.parameter.MealsUiStateParameterPreview
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.UiStateWrapper
import mende273.foody.ui.state.UIState
import mende273.foody.ui.theme.FoodyTheme

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouritesViewModel,
    windowSize: WindowSizeClass,
    onMealClicked: (Long) -> Unit
) {
    val uiState by viewModel.meals.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadData()
    })

    ScreenContents(
        modifier = modifier,
        windowSize = windowSize,
        uiState = uiState,
        onMealClicked = onMealClicked
    )
}

@Composable
private fun ScreenContents(
    modifier: Modifier,
    windowSize: WindowSizeClass,
    uiState: UIState<List<Meal>>,
    onMealClicked: (Long) -> Unit
) {
    Box(modifier.padding(vertical = windowSize.getTopPadding())) {
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
            windowSize = previewModel.windowSize,
            uiState = previewModel.uiState,
            onMealClicked = {}
        )
    }
}
