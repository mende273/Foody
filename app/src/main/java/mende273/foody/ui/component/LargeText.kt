package mende273.foody.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mende273.foody.R
import mende273.foody.ui.theme.largeTextStyle

@Composable
fun LargeText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = largeTextStyle()
    )
}

@Preview(showBackground = true)
@Composable
private fun LargeTextPreview() {
    LargeText(text = stringResource(id = R.string.app_name))
}
