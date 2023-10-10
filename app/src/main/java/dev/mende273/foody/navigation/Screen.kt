package dev.mende273.foody.navigation

sealed class Screen(val route: String) {
    data object Meals : Screen("home")
    data object Search : Screen("search")
    data object RandomMeal : Screen("random")
    data object Favourites : Screen("favorites")
}
