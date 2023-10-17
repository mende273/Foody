package mende273.foody.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import mende273.foody.util.exception.WrongArgumentNumberException

sealed class Screen(val route: String) {
    data object Meals : Screen("home")
    data object Search : Screen("search")
    data object RandomMeal : Screen("random")
    data object Favourites : Screen("favorites")

    data object FilterMealsByCategory : Screen("category/{name}") {

        const val NAME_ARGUMENT = "name"

        override fun getNavArguments(): List<NamedNavArgument> {
            return listOf(
                navArgument(NAME_ARGUMENT) { type = NavType.StringType }
            )
        }
    }

    open fun getNavArguments(): List<NamedNavArgument> = emptyList()

    fun getRoute(vararg id: String): String {
        return getRouteWithValues(*id)
    }

    private fun getRouteWithValues(vararg values: String): String {
        if (values.size != getNavArguments().size) {
            throw WrongArgumentNumberException()
        }

        val preparedRoute = when (getNavArguments().isEmpty()) {
            true -> route
            false -> {
                val builder = StringBuilder()
                values.forEach {
                    builder.append("/$it")
                }

                route.substringBefore("/") + builder.toString()
            }
        }

        return preparedRoute
    }
}
