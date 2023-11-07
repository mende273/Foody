package mende273.foody.ui.screen.meals.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MealDetailsComponent
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.state.UIState

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

    when (uiState) {
        is UIState.Error -> ErrorComponent(
            modifier = Modifier.fillMaxSize(),
            text = (uiState as UIState.Error).errorMessage
        )

        is UIState.Loading -> ProgressBar(Modifier.fillMaxSize())
        is UIState.Success ->
            MealDetailsComponent(
                modifier = modifier,
                mealDetails = (uiState as UIState.Success<MealDetails>).data,
                windowSize = windowSize,
                isBackButtonEnabled = true,
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
}
