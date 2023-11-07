package mende273.foody.ui.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import mende273.foody.ui.screen.favourites.FavouritesScreen
import mende273.foody.ui.screen.meals.MealsScreen
import mende273.foody.ui.screen.meals.details.MealDetailsScreen
import mende273.foody.ui.screen.meals.filter.FilterMealsScreen
import mende273.foody.ui.screen.meals.filter.FilterType
import mende273.foody.ui.screen.meals.image.FullScreenImage
import mende273.foody.ui.screen.random.RandomMealScreen
import mende273.foody.ui.screen.search.SearchScreen
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    windowSize: WindowSizeClass,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current

    NavHost(
        navController,
        startDestination = Screen.Meals.route
    ) {
        composable(Screen.Meals.route) {
            MealsScreen(
                modifier = modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onMealClicked = {
                    navController.navigate(Screen.MealDetails.getRoute(it))
                }
            )
        }

        composable(Screen.RandomMeal.route) {
            RandomMealScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onHeaderImageClicked = {
                    val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screen.FullScreenImage.getRoute(encodedUrl))
                },
                onCategoryClicked = { category ->
                    navController.navigate(
                        Screen.FilterMeals.getRoute(category, FilterType.CATEGORY.name)
                    )
                },
                onAreaClicked = {
                    navController.navigate(
                        Screen.FilterMeals.getRoute(it, FilterType.AREA.name)
                    )
                },
                onVideoClicked = { startImplicitIntent(context, it) },
                onIngredientClicked = { ingredient ->
                    navController.navigate(
                        Screen.FilterMeals.getRoute(ingredient, FilterType.INGREDIENT.name)
                    )
                },
                onSourceClicked = { startImplicitIntent(context, it) }
            )
        }

        composable(Screen.MealDetails.route) {
            MealDetailsScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                mealId = it.getArgument(Screen.MealDetails.MEAL_ID_ARGUMENT),
                windowSize = windowSize,
                onHeaderImageClicked = { imageUrl ->
                    val encodedUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screen.FullScreenImage.getRoute(encodedUrl))
                },
                onCategoryClicked = { category ->
                    navController.navigate(
                        Screen.FilterMeals.getRoute(category, FilterType.CATEGORY.name)
                    )
                },
                onAreaClicked = { area ->
                    navController.navigate(
                        Screen.FilterMeals.getRoute(area, FilterType.AREA.name)
                    )
                },
                onVideoClicked = { url -> startImplicitIntent(context, url) },
                onSourceClicked = { url -> startImplicitIntent(context, url) },
                onIngredientClicked = { ingredient ->
                    navController.navigate(
                        Screen.FilterMeals.getRoute(ingredient, FilterType.INGREDIENT.name)
                    )
                },
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                modifier = modifier.padding(
                    bottom = innerPadding.calculateBottomPadding()
                ),
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onMealClicked = { mealId ->
                    navController.navigate(Screen.MealDetails.getRoute(mealId))
                }
            )
        }

        composable(Screen.Favourites.route) {
            FavouritesScreen(
                modifier = modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onMealClicked = { mealId ->
                    navController.navigate(Screen.MealDetails.getRoute(mealId))
                }
            )
        }

        composable(Screen.FilterMeals.route) {
            FilterMealsScreen(
                modifier = modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                viewModel = koinNavViewModel(),
                name = it.getArgument(Screen.FilterMeals.NAME_ARGUMENT),
                filterType = FilterType.valueOf(
                    it.getArgument(Screen.FilterMeals.FILTER_TYPE_ARGUMENT)
                ),
                windowSize = windowSize,
                onMealClicked = { mealId ->
                    navController.navigate(Screen.MealDetails.getRoute(mealId))
                },
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.FullScreenImage.route) {
            FullScreenImage(
                modifier = modifier,
                imageUrl = it.getArgument(Screen.FullScreenImage.URL_ARGUMENT),
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }
    }
}

private fun NavBackStackEntry.getArgument(key: String): String =
    this.arguments?.getString(key) ?: ""

private fun startImplicitIntent(context: Context, url: String) {
    context.startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
    )
}
