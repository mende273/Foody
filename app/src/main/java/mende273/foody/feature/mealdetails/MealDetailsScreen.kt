package mende273.foody.feature.mealdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.common.state.UIState
import mende273.foody.common.ui.component.mealdetails.MealDetailsComponent
import mende273.foody.common.ui.component.uistatewrapper.UiStateWrapper
import mende273.foody.common.ui.preview.model.MealDetailsScreenPreviewModel
import mende273.foody.common.ui.preview.parameter.MealDetailsScreenParameterProvider
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.core.domain.model.MealDetails

@Composable
fun MealDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MealDetailsViewModel,
    mealId: Long,
    windowSize: WindowSizeClass,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit,
    onIngredientClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = mealId, block = {
        viewModel.init(mealId)
    })

    ScreenContents(
        modifier = modifier,
        uiState = uiState,
        windowSize = windowSize,
        isFavourite = isFavorite,
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

@PreviewLightDark
@PreviewScreenSizes
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
