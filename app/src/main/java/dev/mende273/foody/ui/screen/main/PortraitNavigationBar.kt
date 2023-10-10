package dev.mende273.foody.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.mende273.foody.ui.theme.LightThemePrimary
import dev.mende273.foody.ui.theme.MediumDarkGreyColor
import dev.mende273.foody.ui.theme.NavigationBarSelectedColor

@Composable
fun PortraitNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>,
    currentMenuItem: Int,
    onMenuItemClick: (Int, NavigationMenuItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        content = {
            navigationMenuItems.forEachIndexed { index, menuItem ->
                NavigationBarItem(
                    modifier = Modifier.testTag(
                        "test_tag_menu_item_${stringResource(id = menuItem.title)}"
                    ),
                    selected = currentMenuItem == index,
                    onClick = {
                        onMenuItemClick(index, menuItem)
                    },
                    label = { Text(text = stringResource(id = menuItem.title)) },
                    enabled = true,
                    icon = {
                        menuItem.icon?.let { painterResource(id = menuItem.icon) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(id = menuItem.title)
                            )
                        }
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = LightThemePrimary,
                        unselectedIconColor = MediumDarkGreyColor,
                        selectedTextColor = NavigationBarSelectedColor,
                        unselectedTextColor = MediumDarkGreyColor,
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
    )
}

/*
private fun getTestTagForMenuItem(menuItem: NavigationMenuItem): String {
    val itemName = when (menuItem) {
        NavigationMenuItem.MEALS -> "meals"
        NavigationMenuItem.RANDOM_MEAL -> "random_meal"
        NavigationMenuItem.SEARCH -> "search"
        NavigationMenuItem.FAVORITES -> "favourites"
    }
    return "test_tag_menu_item_$itemName"
}
*/
