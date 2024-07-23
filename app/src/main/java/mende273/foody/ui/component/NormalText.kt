package mende273.foody.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.R
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.largeTextStyle
import mende273.foody.ui.theme.mediumTextStyle
import mende273.foody.ui.theme.normalTextStyle

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textStyle: TextStyle = normalTextStyle(),
    fontWeight: FontWeight = FontWeight.Normal,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = fontWeight,
        textAlign = textAlign,
        style = textStyle
    )
}

@PreviewLightDark
@Composable
private fun NormalTextPreview() {
    FoodyTheme {
        NormalText(text = stringResource(id = R.string.app_name))
    }
}

@PreviewLightDark
@Composable
private fun NormalTextWithMediumTextStyle() {
    FoodyTheme {
        NormalText(
            text = stringResource(id = R.string.app_name),
            textStyle = mediumTextStyle()
        )
    }
}

@PreviewLightDark
@Composable
private fun NormalTextWithLargeTextStyle() {
    FoodyTheme {
        NormalText(
            text = stringResource(id = R.string.app_name),
            textStyle = largeTextStyle()
        )
    }
}

@PreviewLightDark
@Composable
private fun NormalTextWithSemiBoldFontWeight() {
    FoodyTheme {
        NormalText(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.SemiBold
        )
    }
}
