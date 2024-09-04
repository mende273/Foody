package mende273.foody.feature.meals

import androidx.annotation.StringRes
import mende273.foody.R

enum class Filter(@StringRes val title: Int) {
    CATEGORY(R.string.filter_category),
    AREA(R.string.filter_area),
    FIRST_LETTER(R.string.first_letter)
}
