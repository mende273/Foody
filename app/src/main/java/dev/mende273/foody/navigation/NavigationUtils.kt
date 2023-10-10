package dev.mende273.foody.navigation

import dev.mende273.foody.ui.screen.main.NavigationMenuItem

fun NavigationMenuItem.getRoute(): String =
    when (this) {
        NavigationMenuItem.MEALS -> Screen.Meals.route
        NavigationMenuItem.RANDOM_MEAL -> Screen.RandomMeal.route
        NavigationMenuItem.SEARCH -> Screen.Search.route
        NavigationMenuItem.FAVORITES -> Screen.Favourites.route
    }
