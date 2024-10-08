package mende273.foody.common.ui.component.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import mende273.foody.common.R
import mende273.foody.common.ui.theme.FoodyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    modifier: Modifier,
    searchText: String,
    isSearchBarActive: Boolean,
    mealsSize: Int,
    onQueryChanged: (String) -> Unit,
    onIsSearchBarActiveChanged: (Boolean) -> Unit,
    onClearText: () -> Unit
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText,
                onQueryChange = { onQueryChanged(it) },
                onSearch = { onIsSearchBarActiveChanged(false) },
                expanded = isSearchBarActive,
                onExpandedChange = onIsSearchBarActiveChanged,
                enabled = true,
                placeholder = {
                    Text(text = stringResource(id = R.string.search_bar_hint))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.cd_search_icon)
                    )
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
                interactionSource = null,
            )
        },
        expanded = isSearchBarActive,
        onExpandedChange = onIsSearchBarActiveChanged,
        modifier = modifier,
        shape = SearchBarDefaults.inputFieldShape,
        colors = SearchBarColors(
            containerColor = Color.LightGray,
            dividerColor = Color.LightGray
        ),
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
        content = {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(id = R.string.search_results, mealsSize))
            }
        },
    )
}

@PreviewLightDark
@Composable
private fun SearchBarComponentPreview(
    @PreviewParameter(SearchBarComponentParameterProvider::class) isSearchBarActive: Boolean
) {
    FoodyTheme {
        SearchBarComponent(
            modifier = Modifier.fillMaxWidth(),
            searchText = "text",
            isSearchBarActive = isSearchBarActive,
            mealsSize = 0,
            onQueryChanged = {},
            onIsSearchBarActiveChanged = {},
            onClearText = {}
        )
    }
}
