package mende273.foody.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mende273.foody.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    isBackButtonEnabled: Boolean = false,
    onNavigateBackClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (isBackButtonEnabled) {
                BackNavigationButton(navigateBackEvent = { onNavigateBackClicked() })
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

/*
@Composable
private fun TopBarMenuItem(
    menuItem: MenuItem,
    menuItemEvent: (MenuItem) -> Unit = {}
) {
    IconButton(
        modifier = Modifier.testTag(
            "test_tag_menu_item_${stringResource(id = menuItem.titleTextId)}"
        ),
        onClick = { menuItemEvent(menuItem) }
    ) {
        Icon(
            painter = painterResource(id = menuItem.icon),
            contentDescription = stringResource(id = menuItem.titleTextId),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}*/

@Composable
private fun BackNavigationButton(navigateBackEvent: () -> Unit) {
    IconButton(onClick = { navigateBackEvent() }) {
        Icon(Icons.Filled.ArrowBack, "navigate back")
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(title = stringResource(id = R.string.app_name))
}

@Preview
@Composable
private fun TopBarWithBackEnabledPreview() {
    TopBar(title = stringResource(id = R.string.app_name), isBackButtonEnabled = true)
}

@Preview
@Composable
private fun BackNavigationButtonPreview() {
    BackNavigationButton {}
}