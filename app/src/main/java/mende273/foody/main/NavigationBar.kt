package mende273.foody.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
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
import mende273.foody.R
import mende273.foody.common.navigation.Screen
import mende273.foody.common.ui.theme.LightThemePrimary
import mende273.foody.common.ui.theme.MediumDarkGreyColor
import mende273.foody.common.ui.theme.NavigationBarSelectedColor

@Composable
fun NavigationBar(
    currentScreen: Screen?,
    isPortrait: Boolean,
    onNavigateToScreen: (Screen) -> Unit
) {
    val navigationMenuItems = enumValues<NavigationMenuItem>()

    if (isPortrait) {
        PortraitNavigationBar(
            navigationMenuItems = navigationMenuItems,
            currentScreen = currentScreen,
            onMenuItemClick = onNavigateToScreen
        )
    } else {
        LandscapeNavigationBar(
            navigationMenuItems = navigationMenuItems,
            currentScreen = currentScreen,
            onMenuItemClick = onNavigateToScreen
        )
    }
}

@Composable
private fun PortraitNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>,
    currentScreen: Screen?,
    onMenuItemClick: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        content = {
            navigationMenuItems.forEach { menuItem ->
                val screenForMenuItem = menuItem.screen
                NavigationBarItem(
                    modifier = Modifier.testTag(
                        "test_tag_menu_item_${stringResource(id = menuItem.title)}"
                    ),
                    selected = currentScreen == screenForMenuItem,
                    onClick = {
                        onMenuItemClick(screenForMenuItem)
                    },
                    label = { Text(text = stringResource(id = menuItem.title)) },
                    enabled = true,
                    icon = {
                        Icon(
                            painter = painterResource(id = menuItem.icon),
                            contentDescription = stringResource(id = menuItem.title)
                        )
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
    currentScreen: Screen?,
    onMenuItemClick: (Screen) -> Unit
) {
    NavigationRail(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        navigationMenuItems.forEach { menuItem ->
            val screenForMenuItem = menuItem.screen
            NavigationRailItem(
                modifier = Modifier.testTag(
                    "test_tag_menu_item_${stringResource(id = menuItem.title)}"
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = menuItem.icon),
                        contentDescription = stringResource(id = menuItem.title)
                    )
                },
                label = { Text(text = stringResource(id = menuItem.title)) },
                selected = currentScreen == screenForMenuItem,
                onClick = { onMenuItemClick(screenForMenuItem) },
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

enum class NavigationMenuItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screen: Screen
) {
    MEALS(R.string.screen_title_meals, R.drawable.food, Screen.Home),
    RANDOM_MEAL(R.string.screen_title_random, R.drawable.random, Screen.Random),
    SEARCH(R.string.screen_title_search, R.drawable.search, Screen.Search),
    FAVORITES(R.string.screen_title_favourites, R.drawable.favourite, Screen.Favorites)
}
