package mende273.foody.ui.screen.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.R
import mende273.foody.domain.model.Meal
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.state.UIState
import mende273.foody.util.getGridCellsCount

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

    Box(modifier) {
        when (uiState) {
            is UIState.Error -> ErrorComponent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.normal_padding)),
                text = (uiState as UIState.Error).errorMessage
            )

            is UIState.Loading -> ProgressBar()

            is UIState.Success -> {
                MealsGrid(
                    gridCellsCount = windowSize.getGridCellsCount(),
                    meals = (uiState as UIState.Success<List<Meal>>).data,
                    onMealClicked = {
                        onMealClicked(it)
                    }
                )
            }
        }
    }
}
