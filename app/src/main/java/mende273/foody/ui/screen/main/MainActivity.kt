package mende273.foody.ui.screen.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mende273.foody.navigation.AppNavigation
import mende273.foody.navigation.Screen
import mende273.foody.ui.theme.FoodyTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val windowSize = calculateWindowSizeClass(this)

            var navigationBarVisible by remember { mutableStateOf(false) }

            var currentMenuItemIndex by rememberSaveable { mutableIntStateOf(0) }

            val isPortrait = windowSize.widthSizeClass <= WindowWidthSizeClass.Medium

            navigationBarVisible = isRouteFromBottomBarMenu(
                navController.currentBackStackEntryAsState()
            )

            FoodyTheme {
                Scaffold(
                    content = {
                        AppNavigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .navigationBarsPadding()
                                .background(MaterialTheme.colorScheme.background),
                            navController = navController,
                            windowSize = windowSize
                        )
                    },
                    bottomBar = {
                        if (isPortrait) {
                            AnimatedVisibility(
                                visible = navigationBarVisible,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it })
                            ) {
                                NavigationBar(
                                    navController = navController,
                                    isPortrait = true,
                                    currentMenuItemIndex = currentMenuItemIndex,
                                    onUpdateCurrentMenuItemIndex = { index ->
                                        currentMenuItemIndex = index
                                    }
                                )
                            }
                        }
                    }
                )

                if (!isPortrait) {
                    AnimatedVisibility(
                        visible = navigationBarVisible,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
                        NavigationBar(
                            navController = navController,
                            isPortrait = false,
                            currentMenuItemIndex = currentMenuItemIndex,
                            onUpdateCurrentMenuItemIndex = { index ->
                                currentMenuItemIndex = index
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun isRouteFromBottomBarMenu(currentBackStackEntry: State<NavBackStackEntry?>): Boolean {
    val currentRoute = currentBackStackEntry.value?.destination?.route
    return currentRoute?.let { route ->
        return@let (
            route == Screen.Meals.route ||
                route == Screen.RandomMeal.route ||
                route == Screen.Favourites.route ||
                route == Screen.Search.route
            )
    } ?: false
}