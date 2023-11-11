package mende273.foody.ui.screen.meals.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.domain.model.Meal
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.TopBar
import mende273.foody.ui.component.UiStateWrapper
import mende273.foody.ui.preview.annotations.ScreenPreviews
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.preview.model.MealsUiStatePreviewModel
import mende273.foody.ui.preview.parameter.MealsUiStateParameterPreview
import mende273.foody.ui.state.UIState
import mende273.foody.ui.theme.FoodyTheme
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

    FilterMealsScreenContents(
        modifier = modifier,
        headerTitle = headerTitle?.let { stringResource(id = it, name) } ?: name,
        uiState = uiState,
        windowSize = windowSize,
        onNavigateBackClicked = { onNavigateBackClicked() },
        onMealClicked = { onMealClicked(it) }
    )
}

@Composable
private fun FilterMealsScreenContents(
    modifier: Modifier,
    headerTitle: String,
    uiState: UIState<List<Meal>>,
    windowSize: WindowSizeClass,
    onNavigateBackClicked: () -> Unit,
    onMealClicked: (String) -> Unit
) {
    Column(modifier = modifier) {
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = headerTitle,
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

@ThemePreviews
@ScreenPreviews
@Composable
private fun FilterMealsScreenPreview(
    @PreviewParameter(MealsUiStateParameterPreview::class) previewModel: MealsUiStatePreviewModel
) {
    FoodyTheme {
        FilterMealsScreenContents(
            modifier = Modifier.fillMaxSize(),
            headerTitle = "Filter meals",
            uiState = previewModel.uiState,
            windowSize = previewModel.windowSize,
            onNavigateBackClicked = {},
            onMealClicked = {}
        )
    }
}
