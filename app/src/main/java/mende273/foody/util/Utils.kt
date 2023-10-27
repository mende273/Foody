package mende273.foody.util

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import mende273.foody.ui.state.UIState

fun WindowSizeClass.getGridCellsCount(): Int {
    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> GRID_CELLS_COUNT_IN_PORTRAIT
        false -> GRID_CELLS_COUNT_IN_LANDSCAPE
    }
}

fun <T> Result<T>.toUIState(): UIState<T> {
    return this.fold(
        onSuccess = {
            UIState.Success(it)
        },
        onFailure = {
            UIState.Error(it.message ?: ERROR_LOADING_DATA)
        }
    )
}
