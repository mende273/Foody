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
import androidx.navigation.toRoute
import mende273.feature.meals.MealsScreen
import mende273.foody.feature.favorites.FavouritesScreen
import mende273.foody.feature.filtermeals.FilterMealsScreen
import mende273.foody.feature.filtermeals.FilterType
import mende273.foody.feature.fullscreenimage.FullScreenImage
import mende273.foody.feature.mealdetails.MealDetailsScreen
import mende273.foody.feature.random.RandomMealScreen
import mende273.foody.feature.search.SearchScreen
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
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            MealsScreen(
                modifier = modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onMealClicked = { mealId ->
                    navController.navigate(Screen.MealDetails(mealId))
                }
            )
        }

        composable<Screen.Random> {
            RandomMealScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onHeaderImageClicked = { imageUrl ->
                    navController.navigate(Screen.FullScreenImage(imageUrl))
                },
                onCategoryClicked = { category ->
                    navController.navigate(
                        Screen.FilterMeals(category, FilterType.CATEGORY.name)
                    )
                },
                onAreaClicked = {
                    navController.navigate(
                        Screen.FilterMeals(it, FilterType.AREA.name)
                    )
                },
                onVideoClicked = { startImplicitIntent(context, it) },
                onIngredientClicked = { ingredient ->
                    navController.navigate(
                        Screen.FilterMeals(ingredient, FilterType.INGREDIENT.name)
                    )
                },
                onSourceClicked = { startImplicitIntent(context, it) }
            )
        }

        composable<Screen.MealDetails> { backStack ->
            MealDetailsScreen(
                modifier = modifier,
                viewModel = koinNavViewModel(),
                mealId = backStack.toRoute<Screen.MealDetails>().mealId,
                windowSize = windowSize,
                onHeaderImageClicked = { imageUrl ->
                    navController.navigate(Screen.FullScreenImage(imageUrl))
                },
                onCategoryClicked = { category ->
                    navController.navigate(
                        Screen.FilterMeals(category, FilterType.CATEGORY.name)
                    )
                },
                onAreaClicked = { area ->
                    navController.navigate(
                        Screen.FilterMeals(area, FilterType.AREA.name)
                    )
                },
                onVideoClicked = { url -> startImplicitIntent(context, url) },
                onSourceClicked = { url -> startImplicitIntent(context, url) },
                onIngredientClicked = { ingredient ->
                    navController.navigate(
                        Screen.FilterMeals(ingredient, FilterType.INGREDIENT.name)
                    )
                },
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }

        composable<Screen.Search> {
            SearchScreen(
                modifier = modifier.padding(
                    bottom = innerPadding.calculateBottomPadding()
                ),
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onMealClicked = { mealId -> navController.navigate(Screen.MealDetails(mealId)) }
            )
        }

        composable<Screen.Favorites> {
            FavouritesScreen(
                modifier = modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                viewModel = koinNavViewModel(),
                windowSize = windowSize,
                onMealClicked = { mealId -> navController.navigate(Screen.MealDetails(mealId)) }
            )
        }

        composable<Screen.FilterMeals> { backStack ->
            val filterMeals = backStack.toRoute<Screen.FilterMeals>()
            FilterMealsScreen(
                modifier = modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                viewModel = koinNavViewModel(),
                name = filterMeals.name,
                filterType = FilterType.valueOf(filterMeals.filterType),
                windowSize = windowSize,
                onMealClicked = { navController.navigate(Screen.MealDetails(it)) },
                onNavigateBackClicked = { navController.popBackStack() }
            )
        }

        composable<Screen.FullScreenImage> { backStack ->
            FullScreenImage(
                modifier = modifier,
                imageUrl = backStack.toRoute<Screen.FullScreenImage>().url,
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
