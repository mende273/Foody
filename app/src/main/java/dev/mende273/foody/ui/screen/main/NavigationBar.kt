package dev.mende273.foody.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import dev.mende273.foody.navigation.getRoute
import dev.mende273.foody.ui.theme.LightThemePrimary
import dev.mende273.foody.ui.theme.MediumDarkGreyColor
import dev.mende273.foody.ui.theme.NavigationBarSelectedColor

@Composable
fun NavigationBar(
    navController: NavHostController,
    isPortrait: Boolean,
    currentMenuItemIndex: Int = 0,
    onUpdateCurrentMenuItemIndex: (Int) -> Unit = {}
) {
    val navigationMenuItems = enumValues<NavigationMenuItem>()

    if (isPortrait) {
        PortraitNavigationBar(
            navigationMenuItems = navigationMenuItems,
            currentMenuItem = currentMenuItemIndex,
            onMenuItemClick = { index, menuItem ->
                onUpdateCurrentMenuItemIndex(index)
                navController.navigateFromNavigationBar(menuItem.getRoute())
            }
        )
    } else {
        LandscapeNavigationBar(
            navigationMenuItems = navigationMenuItems,
            currentMenuItem = currentMenuItemIndex,
            onMenuItemClick = { index, menuItem ->
                onUpdateCurrentMenuItemIndex(index)
                navController.navigateFromNavigationBar(menuItem.getRoute())
            }
        )
    }
}

@Composable
private fun PortraitNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>,
    currentMenuItem: Int,
    onMenuItemClick: (Int, NavigationMenuItem) -> Unit
) {
    androidx.compose.material3.NavigationBar(
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

@Composable
private fun LandscapeNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>,
    currentMenuItem: Int,
    onMenuItemClick: (Int, NavigationMenuItem) -> Unit
) {
    NavigationRail(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        navigationMenuItems.forEachIndexed { index, menuItem ->
            NavigationRailItem(
                modifier = Modifier.testTag(
                    "test_tag_menu_item_${stringResource(id = menuItem.title)}"
                ),
                icon = {
                    menuItem.icon?.let { painterResource(id = menuItem.icon) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = stringResource(id = menuItem.title)
                        )
                    }
                },
                label = { Text(text = stringResource(id = menuItem.title)) },
                selected = currentMenuItem == index,
                onClick = { onMenuItemClick(index, menuItem) },
                alwaysShowLabel = true,
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = LightThemePrimary,
                    unselectedIconColor = MediumDarkGreyColor,
                    selectedTextColor = NavigationBarSelectedColor,
                    unselectedTextColor = MediumDarkGreyColor,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

private fun NavHostController.navigateFromNavigationBar(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
