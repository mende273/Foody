package mende273.foody.feature.random

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.common.state.UIState
import mende273.foody.common.ui.component.mealdetails.MealDetailsComponent
import mende273.foody.common.ui.component.uistatewrapper.UiStateWrapper
import mende273.foody.common.ui.preview.model.MealDetailsScreenPreviewModel
import mende273.foody.common.ui.preview.parameter.MealDetailsScreenParameterProvider
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.core.domain.model.MealDetails

@Composable
fun RandomMealScreen(
    modifier: Modifier = Modifier,
    viewModel: RandomMealViewModel,
    windowSize: WindowSizeClass,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onIngredientClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit
) {
    //  val mealFromLocalDb by viewModel.mealFromLocalDb.collectAsStateWithLifecycle()
    val isMealFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContents(
        modifier = modifier,
        uiState = uiState,
        windowSize = windowSize,
        isFavourite = isMealFavorite,
        onHeaderImageClicked = { onHeaderImageClicked(it) },
        onCategoryClicked = { onCategoryClicked(it) },
        onAreaClicked = { onAreaClicked(it) },
        onVideoClicked = { onVideoClicked(it) },
        onSourceClicked = { onSourceClicked(it) },
        onIngredientClicked = { onIngredientClicked(it) },
        onFavouriteClicked = { viewModel.toggleFavourite() }
    )
}

@Composable
private fun ScreenContents(
    modifier: Modifier,
    uiState: UIState<MealDetails>,
    windowSize: WindowSizeClass,
    isFavourite: Boolean,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit,
    onIngredientClicked: (String) -> Unit,
    onFavouriteClicked: () -> Unit
) {
    UiStateWrapper(uiState = uiState) { mealDetails ->
        MealDetailsComponent(
            modifier = modifier,
            mealDetails = mealDetails,
            windowSize = windowSize,
            isBackButtonEnabled = false,
            isFavourite = isFavourite,
            onHeaderImageClicked = { onHeaderImageClicked(it) },
            onCategoryClicked = { onCategoryClicked(it) },
            onAreaClicked = { onAreaClicked(it) },
            onVideoClicked = { onVideoClicked(it) },
            onIngredientClicked = { onIngredientClicked(it) },
            onSourceClicked = { onSourceClicked(it) },
            onFavouriteClicked = { onFavouriteClicked() }
        )
    }
}

@PreviewLightDark
@Composable
private fun ScreenPreview(
    @PreviewParameter(MealDetailsScreenParameterProvider::class)
    previewModel: MealDetailsScreenPreviewModel
) {
    FoodyTheme {
        ScreenContents(
            modifier = Modifier.fillMaxSize(),
            uiState = previewModel.uiState,
            windowSize = previewModel.windowSizeClass,
            isFavourite = previewModel.isFavourite,
            onHeaderImageClicked = {},
            onCategoryClicked = {},
            onAreaClicked = {},
            onVideoClicked = {},
            onSourceClicked = {},
            onIngredientClicked = {},
            onFavouriteClicked = {}
        )
    }
}
