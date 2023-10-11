package dev.mende273.foody.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.mende273.foody.ui.screen.favourites.FavouritesScreen
import dev.mende273.foody.ui.screen.meals.MealsScreen
import dev.mende273.foody.ui.screen.random.RandomMealScreen
import dev.mende273.foody.ui.screen.search.SearchScreen
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController,
        startDestination = Screen.Meals.route,
        modifier = modifier.padding(innerPadding)
    ) {
        composable(Screen.Meals.route) {
            MealsScreen(modifier = modifier, viewModel = koinNavViewModel())
        }

        composable(Screen.RandomMeal.route) {
            RandomMealScreen(modifier = modifier, viewModel = koinNavViewModel())
        }

        composable(Screen.Search.route) {
            SearchScreen(modifier = modifier, viewModel = koinNavViewModel())
        }

        composable(Screen.Favourites.route) {
            FavouritesScreen(modifier = modifier, viewModel = koinNavViewModel())
        }
    }
}
