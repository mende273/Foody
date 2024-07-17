package mende273.foody.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mende273.foody.ui.common.Tab

class TabItemsParameterProvider : PreviewParameterProvider<Array<Tab>> {
    override val values: Sequence<Array<Tab>> = sequenceOf(
        arrayOf(
            Tab("Beef"),
            Tab("Breakfast"),
            Tab("Chicken"),
            Tab("Dessert"),
            Tab("Goat")
        ),
        arrayOf(
            Tab("American"),
            Tab("British"),
            Tab("Canadian"),
            Tab("Croatian"),
            Tab("Dutch")
        ),
        arrayOf(
            Tab("A"),
            Tab("B"),
            Tab("C"),
            Tab("D"),
            Tab("E")
        )
    )
}
