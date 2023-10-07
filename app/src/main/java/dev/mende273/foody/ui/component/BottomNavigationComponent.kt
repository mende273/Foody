package dev.mende273.foody.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.mende273.foody.R
import dev.mende273.foody.navigation.Screen
import dev.mende273.foody.ui.theme.AccentColor
import dev.mende273.foody.ui.theme.LightThemePrimary
import dev.mende273.foody.ui.theme.MediumDarkGreyColor
import dev.mende273.foody.ui.theme.NavigationBarSelectedColor

@Composable
fun BottomNavigationComponent(
    navHostController: NavHostController
) {
    val bottomNavigationItems = enumValues<BottomNavigationItem>()

    NavigationBar(
        modifier = Modifier,
        contentColor = AccentColor,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.background,
        content = {
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val hierarchy = navBackStackEntry?.destination?.hierarchy

            bottomNavigationItems.forEach { screen ->
                NavigationBarItem(
                    selected = hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navHostController.navigate(screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = { Text(text = stringResource(id = screen.resourceId)) },
                    enabled = true,
                    icon = {
                        screen.icon?.let { painterResource(id = screen.icon) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(id = screen.resourceId)
                            )
                        }
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = LightThemePrimary,
                        unselectedIconColor = MediumDarkGreyColor,
                        selectedTextColor = NavigationBarSelectedColor,
                        unselectedTextColor = MediumDarkGreyColor,
                        indicatorColor = AccentColor
                    )
                )
            }
        }
    )
}

private enum class BottomNavigationItem(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int?
) {
    MEALS(Screen.Meals.route, R.string.screen_title_meals, R.drawable.food),
    RANDOM_MEAL(Screen.RandomMeal.route, R.string.screen_title_random, R.drawable.random),
    SEARCH(Screen.Search.route, R.string.screen_title_search, R.drawable.search),
    FAVORITES(
        Screen.Favourites.route,
        R.string.screen_title_favourites,
        R.drawable.favourite
    )
}
