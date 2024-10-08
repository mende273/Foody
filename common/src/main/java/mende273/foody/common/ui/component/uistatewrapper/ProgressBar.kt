package mende273.foody.common.ui.component.uistatewrapper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.common.ui.theme.AccentColor
import mende273.foody.common.ui.theme.FoodyTheme

@Composable
fun ProgressBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            color = AccentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun ProgressBarPreview() {
    FoodyTheme {
        ProgressBar()
    }
}
