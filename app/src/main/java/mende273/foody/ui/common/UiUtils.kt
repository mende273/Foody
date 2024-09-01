package mende273.foody.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import mende273.foody.ui.state.UIState
import mende273.foody.ui.state.UIStateError
import mende273.foody.ui.theme.spacing

fun WindowSizeClass.getGridCellsCount(): Int {
    val gridCellsCountInPortrait = 2
    val gridCellsCountInLandscape = 4

    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> gridCellsCountInPortrait
        false -> gridCellsCountInLandscape
    }
}

@Composable
fun WindowSizeClass.getTopPadding(): Dp {
    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> MaterialTheme.spacing.normal
        false -> MaterialTheme.spacing.zero
    }
}

fun <T> Result<T>.toUIState(): UIState<T> {
    return this.fold(
        onSuccess = {
            UIState.Success(it)
        },
        onFailure = {
            UIState.Error(UIStateError.GENERIC_ERROR)
        }
    )
}
