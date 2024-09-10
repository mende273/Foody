package mende273.foody.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.navigation.AppNavigation
import mende273.foody.navigation.Screen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val windowSize = calculateWindowSizeClass(this)

            var isNavigationBarVisible by remember { mutableStateOf(false) }

            val isPortrait = windowSize.widthSizeClass == WindowWidthSizeClass.Compact

            val currentNavigationBarDestination = navController
                .currentBackStackEntryAsState()
                .value
                .currentDestinationFromNavigationBar(
                    enumValues<NavigationMenuItem>()
                        .map { it.screen }
                )

            isNavigationBarVisible = currentNavigationBarDestination != null

            FoodyTheme {
                Scaffold(
                    content = { innerPadding ->
                        Row(
                            Modifier
                                .fillMaxSize()
                                .windowInsetsPadding(
                                    WindowInsets.safeDrawing.only(
                                        WindowInsetsSides.Horizontal
                                    )
                                )
                        ) {
                            if (!isPortrait) {
                                NavigationBar(
                                    currentScreen =
                                    currentNavigationBarDestination,
                                    isPortrait = false,
                                    onNavigateToScreen = {
                                        navController.singleTopNavigate(it)
                                    }
                                )
                            }

                            AppNavigation(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background),
                                navController = navController,
                                windowSize = windowSize,
                                innerPadding = innerPadding
                            )
                        }
                    },
                    bottomBar = {
                        if (isPortrait) {
                            AnimatedVisibility(
                                visible = isNavigationBarVisible,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it })
                            ) {
                                NavigationBar(
                                    currentScreen =
                                    currentNavigationBarDestination,
                                    isPortrait = true,
                                    onNavigateToScreen = { navController.singleTopNavigate(it) }
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

private fun NavBackStackEntry?.currentDestinationFromNavigationBar(
    navigationBarItems: List<Screen>
): Screen? {
    var currentDestination: Screen? = null

    navigationBarItems.forEach { item ->
        if (this?.destination?.hierarchy?.any {
            it.hasRoute(item::class)
        } == true
        ) {
            currentDestination = item
        }
    }

    return currentDestination
}

private fun NavHostController.singleTopNavigate(screen: Screen) {
    navigate(screen) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
