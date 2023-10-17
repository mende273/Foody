package mende273.foody.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mende273.foody.R
import mende273.foody.ui.theme.FoodyTheme

@Composable
fun SmallButton(text: String, onClicked: () -> Unit) {
    Button(
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

@Preview(showBackground = true)
@Composable
private fun SmallButtonPreview() {
    SmallButton(text = stringResource(id = R.string.app_name), onClicked = {})
}

@Preview
@Composable
private fun SmallButtonDarkThemePreview() {
    FoodyTheme(darkTheme = true) {
        SmallButton(text = stringResource(id = R.string.app_name), onClicked = {})
    }
}
