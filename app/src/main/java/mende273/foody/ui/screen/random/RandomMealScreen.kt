package mende273.foody.ui.screen.random

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MealDetailsComponent
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.state.UIState

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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is UIState.Error -> ErrorComponent(
            modifier = Modifier.fillMaxSize(),
            text = (uiState as UIState.Error).errorMessage
        )

        is UIState.Loading -> ProgressBar(Modifier.fillMaxSize())
        is UIState.Success -> MealDetailsComponent(
            modifier = modifier,
            mealDetails = (uiState as UIState.Success<MealDetails>).data,
            windowSize = windowSize,
            isBackButtonEnabled = false,
            onHeaderImageClicked = { onHeaderImageClicked(it) },
            onCategoryClicked = { onCategoryClicked(it) },
            onAreaClicked = { onAreaClicked(it) },
            onVideoClicked = { onVideoClicked(it) },
            onIngredientClicked = { onIngredientClicked(it) },
            onSourceClicked = { onSourceClicked(it) }
        )
    }
}
