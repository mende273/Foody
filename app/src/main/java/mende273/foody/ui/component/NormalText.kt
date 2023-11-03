package mende273.foody.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true)
@Composable
private fun NormalTextPreview() {
    NormalText(text = stringResource(id = R.string.app_name))
}

@Preview(showBackground = false)
@Composable
private fun NormalTextDarkThemePreview() {
    FoodyTheme(darkTheme = true) {
        NormalText(text = stringResource(id = R.string.app_name))
    }
}

@Preview(showBackground = true)
@Composable
private fun NormalTextWithMediumTextStyle() {
    NormalText(
        text = stringResource(id = R.string.app_name),
        textStyle = mediumTextStyle()
    )
}

@Preview(showBackground = true)
@Composable
private fun NormalTextWithLargeTextStyle() {
    NormalText(
        text = stringResource(id = R.string.app_name),
        textStyle = largeTextStyle()
    )
}

@Preview(showBackground = true)
@Composable
private fun NormalTextWithSemiBoldFontWeight() {
    NormalText(
        text = stringResource(id = R.string.app_name),
        fontWeight = FontWeight.SemiBold
    )
}
