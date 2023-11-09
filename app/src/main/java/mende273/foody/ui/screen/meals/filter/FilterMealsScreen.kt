package mende273.foody.ui.screen.meals.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.TopBar
import mende273.foody.ui.component.UiStateWrapper
import mende273.foody.util.getGridCellsCount

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

        UiStateWrapper(uiState = uiState) { meals ->
            MealsGrid(
                gridCellsCount = windowSize.getGridCellsCount(),
                meals = meals,
                onMealClicked = { onMealClicked(it) }
            )
        }
    }
}
