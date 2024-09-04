package mende273.foody.common.ui.component.searchbar

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SearchBarComponentParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)
}
