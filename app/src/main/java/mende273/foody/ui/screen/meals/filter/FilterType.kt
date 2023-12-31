package mende273.foody.ui.screen.meals.filter

import androidx.annotation.StringRes
import mende273.foody.R

enum class FilterType(@StringRes val title: Int) {
    CATEGORY(R.string.title_category),
    AREA(R.string.title_area),
    INGREDIENT(R.string.title_ingredient)
}
