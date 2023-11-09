package mende273.foody.util

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import mende273.foody.ui.state.UIState
import mende273.foody.ui.theme.NORMAL_PADDING
import mende273.foody.ui.theme.ZERO_PADDING

fun WindowSizeClass.getGridCellsCount(): Int {
    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> GRID_CELLS_COUNT_IN_PORTRAIT
        false -> GRID_CELLS_COUNT_IN_LANDSCAPE
    }
}

@Composable
fun WindowSizeClass.getTopPadding(): Dp {
    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> NORMAL_PADDING
        false -> ZERO_PADDING
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
