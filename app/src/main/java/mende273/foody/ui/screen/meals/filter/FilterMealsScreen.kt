package mende273.foody.ui.screen.meals.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
fun FilterMealsScreen(
    modifier: Modifier = Modifier,
    viewModel: FilterMealsViewModel,
    windowSize: WindowSizeClass,
    name: String,
    filterType: FilterType,
    onMealClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit
) {
    val headerTitle by viewModel.headerTitle.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = name, block = {
        viewModel.requestData(name, filterType)
    })

    Column(modifier = modifier) {
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = headerTitle?.let { stringResource(id = it, name) } ?: name,
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
