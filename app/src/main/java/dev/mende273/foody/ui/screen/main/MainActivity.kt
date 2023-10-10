package dev.mende273.foody.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.mende273.foody.navigation.AppNavigation
import dev.mende273.foody.navigation.getRoute
import dev.mende273.foody.ui.theme.FoodyTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val windowSize = calculateWindowSizeClass(this)

            var currentMenuItemIndex by rememberSaveable { mutableIntStateOf(0) }

            val isPortrait = windowSize.widthSizeClass <= WindowWidthSizeClass.Medium

            val navigationMenuItems = enumValues<NavigationMenuItem>()

            FoodyTheme {
                Scaffold(
                    content = { innerPadding ->
                        AppNavigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background),
                            navController = navController,
                            innerPadding = innerPadding
                        )
                    },
                    bottomBar = {
                        if (isPortrait) {
                            PortraitNavigationBar(
                                navigationMenuItems = navigationMenuItems,
                                currentMenuItem = currentMenuItemIndex,
                                onMenuItemClick = { index, menuItem ->
                                    currentMenuItemIndex = index
                                    navController.navigateFromNavigationBar(menuItem.getRoute())
                                }
                            )
                        }
                    }
                )

                if (!isPortrait) {
                    LandscapeNavigationBar(
                        navigationMenuItems = navigationMenuItems,
                        currentMenuItem = currentMenuItemIndex,
                        onMenuItemClick = { index, menuItem ->
                            currentMenuItemIndex = index
                            navController.navigateFromNavigationBar(menuItem.getRoute())
                        }
                    )
                }
            }
        }
    }
}

private fun NavHostController.navigateFromNavigationBar(
    route: String
) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
