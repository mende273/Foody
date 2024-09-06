package mende273.feature.meals

import androidx.annotation.StringRes

enum class Filter(@StringRes val title: Int) {
    CATEGORY(R.string.filter_category),
    AREA(R.string.filter_area),
    FIRST_LETTER(R.string.first_letter)
}
