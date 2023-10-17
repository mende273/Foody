package mende273.foody.ui.screen.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import mende273.foody.R
import mende273.foody.navigation.Screen
import mende273.foody.ui.theme.LightThemePrimary
import mende273.foody.ui.theme.MediumDarkGreyColor
import mende273.foody.ui.theme.NavigationBarSelectedColor

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

private enum class NavigationMenuItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    MEALS(R.string.screen_title_meals, R.drawable.food),
    RANDOM_MEAL(R.string.screen_title_random, R.drawable.random),
    SEARCH(R.string.screen_title_search, R.drawable.search),
    FAVORITES(R.string.screen_title_favourites, R.drawable.favourite)
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

private fun NavigationMenuItem.getRoute(): String =
    when (this) {
        NavigationMenuItem.MEALS -> Screen.Meals.route
        NavigationMenuItem.RANDOM_MEAL -> Screen.RandomMeal.route
        NavigationMenuItem.SEARCH -> Screen.Search.route
        NavigationMenuItem.FAVORITES -> Screen.Favourites.route
    }
