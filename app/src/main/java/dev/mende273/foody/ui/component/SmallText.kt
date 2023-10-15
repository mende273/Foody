package dev.mende273.foody.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.mende273.foody.R
import dev.mende273.foody.ui.theme.smallTextStyle

@Composable
fun SmallText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = smallTextStyle()
    )
}

@Preview(showBackground = true)
@Composable
private fun SmallTextPreview() {
    SmallText(text = stringResource(id = R.string.app_name))
}
