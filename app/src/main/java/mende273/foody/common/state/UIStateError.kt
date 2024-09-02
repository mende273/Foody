package mende273.foody.common.state

import androidx.annotation.StringRes
import mende273.foody.R

enum class UIStateError(@StringRes val errorMessage: Int) {
    GENERIC_ERROR(R.string.generic_error),
    NO_ITEMS(R.string.error_no_items_here)
}
