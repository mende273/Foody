package mende273.foody.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import mende273.foody.R
import mende273.foody.ui.theme.normalTextStyle

@Composable
fun SmallText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = normalTextStyle()
    )
}

@Preview(showBackground = true)
@Composable
private fun SmallTextPreview() {
    SmallText(text = stringResource(id = R.string.app_name))
}
