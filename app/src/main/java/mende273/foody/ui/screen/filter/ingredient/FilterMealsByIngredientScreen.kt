package mende273.foody.ui.screen.filter.ingredient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.domain.model.Meals
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.component.TopBar
import mende273.foody.ui.state.UIState
import mende273.foody.util.GRID_CELLS_COUNT_IN_LANDSCAPE
import mende273.foody.util.GRID_CELLS_COUNT_IN_PORTRAIT

@Composable
fun FilterMealsByIngredient(
    modifier: Modifier = Modifier,
    viewModel: FilterMealsByIngredientViewModel,
    windowSize: WindowSizeClass,
    ingredient: String,
    onMealClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = ingredient, block = {
        viewModel.requestData(ingredient)
    })

    Column(modifier = modifier) {
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "Filter By Ingredient: $ingredient",
            isBackButtonEnabled = true,
            onNavigateBackClicked = { onNavigateBackClicked() }
        )

        when (uiState) {
            is UIState.Error -> ErrorComponent(
                modifier = modifier,
                text = (uiState as UIState.Error).errorMessage
            )

            is UIState.Loading -> ProgressBar(modifier)
            is UIState.Success -> {
                val gridCells = if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                    GRID_CELLS_COUNT_IN_PORTRAIT
                } else {
                    GRID_CELLS_COUNT_IN_LANDSCAPE
                }

                MealsGrid(
                    gridCellsCount = gridCells,
                    meals = (uiState as UIState.Success<Meals>).data.meals,
                    onMealClicked = {
                        onMealClicked(it)
                    }
                )
            }
        }
    }
}
