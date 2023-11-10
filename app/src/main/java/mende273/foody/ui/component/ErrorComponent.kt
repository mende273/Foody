package mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mende273.foody.ui.annotations.FontScalePreviews
import mende273.foody.ui.annotations.ThemePreviews
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.largeTextStyle
import mende273.foody.util.ERROR_LOADING_DATA

@Composable
fun ErrorComponent(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        NormalText(text = text, textStyle = largeTextStyle())
    }
}

@ThemePreviews
@FontScalePreviews
@Composable
private fun ErrorComponentPreview() {
    FoodyTheme {
        ErrorComponent(text = ERROR_LOADING_DATA)
    }
}
