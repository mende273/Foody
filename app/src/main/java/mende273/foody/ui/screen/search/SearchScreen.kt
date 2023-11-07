package mende273.foody.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.R
import mende273.foody.domain.model.Meal
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MealsGrid
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.state.UIState
import mende273.foody.util.GRID_CELLS_COUNT_IN_LANDSCAPE
import mende273.foody.util.GRID_CELLS_COUNT_IN_PORTRAIT

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    windowSize: WindowSizeClass,
    onMealClicked: (String) -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var isSearchBarActive by rememberSaveable { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        SearchComponent(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 150.dp)
                .then(
                    if (!isSearchBarActive) {
                        Modifier.padding(dimensionResource(id = R.dimen.normal_padding))
                    } else {
                        Modifier.padding(0.dp)
                    }
                ),
            searchText = searchText,
            isSearchBarActive = isSearchBarActive,
            mealsSize = if (uiState is UIState.Success) {
                (uiState as UIState.Success<List<Meal>>).data.size
            } else {
                0
            },
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
            }
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_padding)))

        when (uiState) {
            is UIState.Error -> ErrorComponent(
                modifier = Modifier.fillMaxSize(),
                text = (uiState as UIState.Error).errorMessage
            )

            is UIState.Loading -> ProgressBar(Modifier.fillMaxSize())
            is UIState.Success -> {
                val gridCells = if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                    GRID_CELLS_COUNT_IN_PORTRAIT
                } else {
                    GRID_CELLS_COUNT_IN_LANDSCAPE
                }

                MealsGrid(
                    gridCellsCount = gridCells,
                    meals = (uiState as UIState.Success<List<Meal>>).data,
                    onMealClicked = {
                        onMealClicked(it)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchComponent(
    modifier: Modifier,
    searchText: String,
    isSearchBarActive: Boolean,
    mealsSize: Int,
    onQueryChanged: (String) -> Unit,
    onIsSearchBarActiveChanged: (Boolean) -> Unit,
    onClearText: () -> Unit
) {
    SearchBar(
        modifier = modifier,
        query = searchText,
        onQueryChange = { onQueryChanged(it) },
        onSearch = { onIsSearchBarActiveChanged(false) },
        active = isSearchBarActive,
        onActiveChange = { onIsSearchBarActiveChanged(it) },
        placeholder = {
            Text(text = stringResource(id = R.string.search_bar_hint))
        },
        trailingIcon = {
            if (isSearchBarActive) {
                Icon(
                    modifier = Modifier.clickable { onClearText() },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.cd_close_search_bar)
                )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.cd_search_icon)
            )
        }
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.search_results, mealsSize))
        }
    }
}
