package dev.mende273.foody.ui.component

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.mende273.foody.R
import dev.mende273.foody.ui.theme.largeTextStyle

@Composable
fun LargeText(text: String) {
    Text(
        modifier = Modifier.wrapContentWidth(),
        text = text,
        style = largeTextStyle()
    )
}

@Preview(showBackground = true)
@Composable
private fun LargeTextPreview() {
    LargeText(text = stringResource(id = R.string.app_name))
}
