package mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.ui.state.UIStateError
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.largeTextStyle

@Composable
fun ErrorComponent(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        NormalText(text = text, textStyle = largeTextStyle())
    }
}

@PreviewLightDark
@PreviewFontScale
@Composable
private fun ErrorComponentPreview() {
    FoodyTheme {
        ErrorComponent(text = stringResource(id = UIStateError.GENERIC_ERROR.errorMessage))
    }
}
