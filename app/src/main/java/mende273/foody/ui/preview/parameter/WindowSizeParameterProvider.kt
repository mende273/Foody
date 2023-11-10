package mende273.foody.ui.preview.parameter

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

class WindowSizeParameterProvider : PreviewParameterProvider<WindowSizeClass> {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override val values: Sequence<WindowSizeClass> = sequenceOf(
        WindowSizeClass.calculateFromSize(
            DpSize(width = 500.dp, height = 1000.dp)
        ),
        WindowSizeClass.calculateFromSize(
            DpSize(width = 1920.dp, height = 1080.dp)
        )
    )
}
