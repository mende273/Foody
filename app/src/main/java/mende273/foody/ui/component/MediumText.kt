package mende273.foody.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import mende273.foody.R
import mende273.foody.ui.theme.mediumTextStyle

@Composable
fun MediumText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = fontWeight,
        style = mediumTextStyle()
    )
}

@Preview
@Composable
private fun MediumTextPreview() {
    MediumText(text = stringResource(id = R.string.app_name))
}
