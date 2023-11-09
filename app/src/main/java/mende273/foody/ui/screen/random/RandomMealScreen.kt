package mende273.foody.ui.screen.random

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.ui.component.MealDetailsComponent
import mende273.foody.ui.component.UiStateWrapper

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
    val mealFromLocalDb by viewModel.mealFromLocalDb.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UiStateWrapper(uiState = uiState) { mealDetails ->
        MealDetailsComponent(
            modifier = modifier,
            mealDetails = mealDetails,
            windowSize = windowSize,
            isBackButtonEnabled = false,
            isFavourite = mealFromLocalDb != null,
            onHeaderImageClicked = { onHeaderImageClicked(it) },
            onCategoryClicked = { onCategoryClicked(it) },
            onAreaClicked = { onAreaClicked(it) },
            onVideoClicked = { onVideoClicked(it) },
            onIngredientClicked = { onIngredientClicked(it) },
            onSourceClicked = { onSourceClicked(it) },
            onFavouriteClicked = { viewModel.toggleFavourite() }
        )
    }
}
