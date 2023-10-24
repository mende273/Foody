package mende273.foody.util

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

fun WindowSizeClass.getGridCellsCount(): Int {
    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> GRID_CELLS_COUNT_IN_PORTRAIT
        false -> GRID_CELLS_COUNT_IN_LANDSCAPE
    }
}
