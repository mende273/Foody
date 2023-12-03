package mende273.foody.ui.screen.meals.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.component.MealDetailsComponent
import mende273.foody.ui.component.UiStateWrapper
import mende273.foody.ui.preview.annotations.ScreenPreviews
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.preview.model.MealDetailsScreenPreviewModel
import mende273.foody.ui.preview.parameter.MealDetailsScreenParameterProvider
import mende273.foody.ui.state.UIState
import mende273.foody.ui.theme.FoodyTheme

@Composable
fun MealDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MealDetailsViewModel,
    mealId: String,
    windowSize: WindowSizeClass,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit,
    onIngredientClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit
) {
    val mealFromLocalDb by viewModel.mealFromLocalDb.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = mealId, block = {
        viewModel.requestData(mealId)
    })

    ScreenContents(
        modifier = modifier,
        uiState = uiState,
        windowSize = windowSize,
        isFavourite = mealFromLocalDb != null,
        onHeaderImageClicked = { onHeaderImageClicked(it) },
        onCategoryClicked = { onCategoryClicked(it) },
        onAreaClicked = { onAreaClicked(it) },
        onVideoClicked = { onVideoClicked(it) },
        onSourceClicked = { onSourceClicked(it) },
        onIngredientClicked = { onIngredientClicked(it) },
        onNavigateBackClicked = { onNavigateBackClicked() },
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
    onNavigateBackClicked: () -> Unit,
    onFavouriteClicked: () -> Unit
) {
    UiStateWrapper(uiState = uiState) { mealDetails ->
        MealDetailsComponent(
            modifier = modifier,
            mealDetails = mealDetails,
            windowSize = windowSize,
            isBackButtonEnabled = true,
            isFavourite = isFavourite,
            onHeaderImageClicked = { onHeaderImageClicked(it) },
            onCategoryClicked = { onCategoryClicked(it) },
            onAreaClicked = { onAreaClicked(it) },
            onVideoClicked = { onVideoClicked(it) },
            onSourceClicked = { onSourceClicked(it) },
            onIngredientClicked = { onIngredientClicked(it) },
            onNavigateBackClicked = { onNavigateBackClicked() },
            onFavouriteClicked = { onFavouriteClicked() }
        )
    }
}

@ThemePreviews
@ScreenPreviews
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
            onNavigateBackClicked = {},
            onFavouriteClicked = {}
        )
    }
}
