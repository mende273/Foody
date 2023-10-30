package mende273.foody.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import mende273.foody.ui.screen.details.MealDetailsScreen
import mende273.foody.ui.screen.favourites.FavouritesScreen
import mende273.foody.ui.screen.filter.area.FilterMealsByAreaScreen
import mende273.foody.ui.screen.filter.category.FilterMealsByCategory
import mende273.foody.ui.screen.image.FullScreenImage
import mende273.foody.ui.screen.meals.MealsScreen
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
                onCategoryClicked = {
                    navController.navigate(Screen.FilterMealsByCategory.getRoute(it))
                },
                onAreaClicked = {
                    navController.navigate(Screen.FilterMealsByArea.getRoute(it))
                },
                onVideoClicked = { startImplicitIntent(context, it) },
                onSourceClicked = { startImplicitIntent(context, it) }
            )
        }

        composable(Screen.MealDetails.route) {
            val mealId = it.arguments?.getString(Screen.MealDetails.MEAL_ID_ARGUMENT)
            MealDetailsScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                mealId = mealId ?: "",
                windowSize = windowSize,
                onHeaderImageClicked = { imageUrl ->
                    val encodedUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screen.FullScreenImage.getRoute(encodedUrl))
                },
                onCategoryClicked = { category ->
                    navController.navigate(Screen.FilterMealsByCategory.getRoute(category))
                },
                onAreaClicked = { area ->
                    navController.navigate(Screen.FilterMealsByArea.getRoute(area))
                },
                onVideoClicked = { url -> startImplicitIntent(context, url) },
                onSourceClicked = { url -> startImplicitIntent(context, url) },
                onNavigateBackClicked = { navController.popBackStack() }
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
                modifier = modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                viewModel = koinNavViewModel(),
                category = categoryName ?: "",
                windowSize = windowSize,
                onMealClicked = { mealId ->
                    navController.navigate(Screen.MealDetails.getRoute(mealId))
                },
                onNavigateBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.FilterMealsByArea.route) {
            val areaName = it.arguments?.getString(Screen.FilterMealsByArea.NAME_ARGUMENT)
            FilterMealsByAreaScreen(
                modifier = modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                viewModel = koinNavViewModel(),
                area = areaName ?: "",
                windowSize = windowSize,
                onMealClicked = { mealId ->
                    navController.navigate(Screen.MealDetails.getRoute(mealId))
                },
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.FullScreenImage.route) {
            val imageUrl = it.arguments?.getString(Screen.FullScreenImage.URL_ARGUMENT)
            FullScreenImage(
                modifier = modifier,
                imageUrl = imageUrl ?: "",
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }
    }
}

private fun startImplicitIntent(context: Context, url: String) {
    context.startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
    )
}
