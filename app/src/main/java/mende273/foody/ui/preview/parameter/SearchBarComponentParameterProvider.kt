package mende273.foody.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SearchBarComponentParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)
}
