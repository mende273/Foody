package mende273.foody.common.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import mende273.foody.common.R
import mende273.foody.common.ui.theme.FoodyTheme

@Composable
fun SmallButton(modifier: Modifier = Modifier, text: String, onClicked: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClicked() },
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(
            top = 4.dp,
            bottom = 4.dp,
            start = 10.dp,
            end = 10.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.background)
    }
}

@PreviewLightDark
@PreviewFontScale
@Composable
private fun SmallButtonPreview() {
    FoodyTheme {
        SmallButton(text = stringResource(id = R.string.placeholder_text), onClicked = {})
    }
}
