package mende273.foody.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    object Home : Screen

    @Serializable
    object Search : Screen

    @Serializable
    object Random : Screen

    @Serializable
    object Favorites : Screen

    @Serializable
    data class FullScreenImage(val url: String) : Screen

    @Serializable
    data class MealDetails(val mealId: Long) : Screen

    @Serializable
    data class FilterMeals(val name: String, val filterType: String) : Screen
}
