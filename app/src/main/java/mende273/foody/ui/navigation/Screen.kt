package mende273.foody.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data object Search : Screen

    @Serializable
    data object Random : Screen

    @Serializable
    data object Favorites : Screen

    @Serializable
    data class FullScreenImage(val url: String) : Screen

    @Serializable
    data class MealDetails(val mealId: Long) : Screen

    @Serializable
    data class FilterMeals(val name: String, val filterType: String) : Screen
}
