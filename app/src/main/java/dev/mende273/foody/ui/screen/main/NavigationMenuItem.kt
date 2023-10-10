package dev.mende273.foody.ui.screen.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.mende273.foody.R

enum class NavigationMenuItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {
    MEALS(R.string.screen_title_meals, R.drawable.food),
    RANDOM_MEAL(R.string.screen_title_random, R.drawable.random),
    SEARCH(R.string.screen_title_search, R.drawable.search),
    FAVORITES(R.string.screen_title_favourites, R.drawable.favourite)
}
