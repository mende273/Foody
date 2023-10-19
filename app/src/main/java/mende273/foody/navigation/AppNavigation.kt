package mende273.foody.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mende273.foody.ui.screen.favourites.FavouritesScreen
import mende273.foody.ui.screen.filter.area.FilterMealsByAreaScreen
import mende273.foody.ui.screen.filter.category.FilterMealsByCategory
import mende273.foody.ui.screen.meals.MealsScreen
import mende273.foody.ui.screen.random.RandomMealScreen
import mende273.foody.ui.screen.search.SearchScreen
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    windowSize: WindowSizeClass
) {
    NavHost(
        navController,
        startDestination = Screen.Meals.route
    ) {
        composable(Screen.Meals.route) {
            MealsScreen(modifier = modifier, viewModel = koinNavViewModel())
        }

        composable(Screen.RandomMeal.route) {
            RandomMealScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onHeaderImageClicked = {
                    // TODO
                },
                onCategoryClicked = {
                    navController.navigate(Screen.FilterMealsByCategory.getRoute(it))
                },
                onAreaClicked = {
                    navController.navigate(Screen.FilterMealsByArea.getRoute(it))
                },
                onTagClicked = {
                    // TODO
                },
                onVideoClicked = {
                    // TODO
                },
                onSourceClicked = {
                    // TODO
                }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(modifier = modifier, viewModel = koinNavViewModel())
        }

        composable(Screen.Favourites.route) {
            FavouritesScreen(modifier = modifier, viewModel = koinNavViewModel())
        }

        composable(Screen.FilterMealsByCategory.route) {
            val categoryName = it.arguments?.getString(Screen.FilterMealsByCategory.NAME_ARGUMENT)
            FilterMealsByCategory(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                category = categoryName ?: "",
                windowSize = windowSize,
                onMealClicked = {
                    // TODO
                },
                onNavigateBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.FilterMealsByArea.route) {
            val areaName = it.arguments?.getString(Screen.FilterMealsByArea.NAME_ARGUMENT)
            FilterMealsByAreaScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                area = areaName ?: "",
                windowSize = windowSize,
                onMealClicked = {
                    // TODO
                },
                onNavigateBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
