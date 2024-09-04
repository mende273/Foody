package mende273.foody.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.common.getGridCellsCount
import mende273.foody.common.state.UIState
import mende273.foody.common.ui.component.mealsgrid.MealsGrid
import mende273.foody.common.ui.component.searchbar.SearchBarComponent
import mende273.foody.common.ui.component.uistatewrapper.UiStateWrapper
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.spacing
import mende273.foody.core.domain.model.Meal

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    windowSize: WindowSizeClass,
    onMealClicked: (Long) -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var isSearchBarActive by rememberSaveable { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContents(
        modifier = modifier,
        isSearchBarActive = isSearchBarActive,
        searchText = searchText,
        uiState = uiState,
        windowSize = windowSize,
        onQueryChanged = {
            searchText = it
            viewModel.onSearchTextChange(searchText)
        },
        onIsSearchBarActiveChanged = { isSearchBarActive = it },
        onClearText = {
            if (searchText.isNotEmpty()) {
                searchText = ""
                viewModel.onClearSearch()
            } else {
                isSearchBarActive = false
            }
        },
        onMealClicked = onMealClicked
    )
}

@Composable
private fun ScreenContents(
    modifier: Modifier,
    isSearchBarActive: Boolean,
    searchText: String,
    uiState: UIState<List<Meal>>,
    windowSize: WindowSizeClass,
    onQueryChanged: (String) -> Unit,
    onIsSearchBarActiveChanged: (Boolean) -> Unit,
    onClearText: () -> Unit,
    onMealClicked: (Long) -> Unit
) {
    Column(modifier = modifier) {
        SearchBarComponent(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 150.dp)
                .then(
                    if (!isSearchBarActive) {
                        Modifier.padding(MaterialTheme.spacing.normal)
                    } else {
                        Modifier.padding(0.dp)
                    }
                ),
            searchText = searchText,
            isSearchBarActive = isSearchBarActive,
            mealsSize = if (uiState is UIState.Success) {
                uiState.data.size
            } else {
                0
            },
            onQueryChanged = {
                onQueryChanged(it)
            },
            onIsSearchBarActiveChanged = { onIsSearchBarActiveChanged(it) },
            onClearText = {
                onClearText()
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))

        UiStateWrapper(uiState = uiState) { meals ->
            MealsGrid(
                gridCellsCount = windowSize.getGridCellsCount(),
                meals = meals,
                onMealClicked = onMealClicked
            )
        }
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun ScreenPreview(
    @PreviewParameter(SearchScreenParameterPreview::class) previewModel: SearchScreenPreviewModel
) {
    FoodyTheme {
        ScreenContents(
            modifier = Modifier.fillMaxSize(),
            isSearchBarActive = false,
            searchText = "beef",
            uiState = previewModel.uiState,
            windowSize = previewModel.windowSize,
            onQueryChanged = {},
            onIsSearchBarActiveChanged = {},
            onClearText = { },
            onMealClicked = {}
        )
    }
}
