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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import mende273.foody.R
import mende273.foody.navigation.Screen
import mende273.foody.ui.theme.LightThemePrimary
import mende273.foody.ui.theme.MediumDarkGreyColor
import mende273.foody.ui.theme.NavigationBarSelectedColor

@Composable
fun NavigationBar(
    navController: NavHostController,
    isPortrait: Boolean
) {
    val navigationMenuItems = enumValues<NavigationMenuItem>()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val hierarchy = navBackStackEntry?.destination?.hierarchy

    if (isPortrait) {
        PortraitNavigationBar(
            navigationMenuItems = navigationMenuItems,
            hierarchy = hierarchy,
            onMenuItemClick = { menuItem ->
                navController.navigateFromNavigationBar(menuItem.getRoute())
            }
        )
    } else {
        LandscapeNavigationBar(
            navigationMenuItems = navigationMenuItems,
            hierarchy = hierarchy,
            onMenuItemClick = { menuItem ->
                navController.navigateFromNavigationBar(menuItem.getRoute())
            }
        )
    }
}

@Composable
private fun PortraitNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>,
    hierarchy: Sequence<NavDestination>?,
    onMenuItemClick: (NavigationMenuItem) -> Unit
) {
    androidx.compose.material3.NavigationBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        content = {
            navigationMenuItems.forEach { menuItem ->
                NavigationBarItem(
                    modifier = Modifier.testTag(
                        "test_tag_menu_item_${stringResource(id = menuItem.title)}"
                    ),
                    selected = hierarchy?.any { it.route == menuItem.getRoute() } == true,
                    onClick = {
                        onMenuItemClick(menuItem)
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
    hierarchy: Sequence<NavDestination>?,
    onMenuItemClick: (NavigationMenuItem) -> Unit
) {
    NavigationRail(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        navigationMenuItems.forEach { menuItem ->
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
                selected = hierarchy?.any { it.route == menuItem.getRoute() } == true,
                onClick = { onMenuItemClick(menuItem) },
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
