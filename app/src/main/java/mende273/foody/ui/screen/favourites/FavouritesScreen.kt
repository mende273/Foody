package mende273.foody.ui.screen.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.UiStateWrapper
import mende273.foody.util.getGridCellsCount
import mende273.foody.util.getTopPadding

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouritesViewModel,
    windowSize: WindowSizeClass,
    onMealClicked: (String) -> Unit
) {
    val uiState by viewModel.meals.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadData()
    })

    Box(modifier.padding(vertical = windowSize.getTopPadding())) {
        UiStateWrapper(uiState = uiState) { meals ->
            MealsGrid(
                gridCellsCount = windowSize.getGridCellsCount(),
                meals = meals,
                onMealClicked = { onMealClicked(it) }
            )
        }
    }
}
