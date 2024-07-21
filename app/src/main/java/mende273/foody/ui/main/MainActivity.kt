package mende273.foody.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mende273.foody.ui.navigation.AppNavigation
import mende273.foody.ui.navigation.Screen
import mende273.foody.ui.theme.FoodyTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val windowSize = calculateWindowSizeClass(this)

            var isNavigationBarVisible by remember { mutableStateOf(false) }

            val isPortrait = windowSize.widthSizeClass == WindowWidthSizeClass.Compact

            val currentBackStack = navController.currentBackStackEntryAsState()

            isNavigationBarVisible = currentBackStack.value.shouldShowNavBar()

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
                                    currentScreen = currentBackStack.value.currentScreen(),
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
                                    currentScreen = currentBackStack.value.currentScreen(),
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

private fun NavDestination?.getRouteName(): String? =
    this?.route
        ?.substringBefore("?")
        ?.substringBefore("/")
        ?.substringAfterLast(".")

private fun NavBackStackEntry?.shouldShowNavBar(): Boolean {
    return this?.destination?.getRouteName()
        ?.let {
            when (it) {
                Screen.Home::class.simpleName,
                Screen.Random::class.simpleName,
                Screen.Favorites::class.simpleName,
                Screen.Search::class.simpleName -> true

                else -> false
            }
        } ?: false
}

private fun NavBackStackEntry?.currentScreen(): Screen {
    var currentDestination: Screen? = null

    this?.destination?.getRouteName()
        ?.let {
            currentDestination = when (it) {
                Screen.Home::class.simpleName -> Screen.Home
                Screen.Random::class.simpleName -> Screen.Random
                Screen.Favorites::class.simpleName -> Screen.Favorites
                Screen.Search::class.simpleName -> Screen.Search
                else -> null
            }
        }
    return currentDestination ?: Screen.Home
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
