package mende273.foody.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mende273.foody.ui.annotations.ThemePreviews
import mende273.foody.ui.theme.AccentColor
import mende273.foody.ui.theme.FoodyTheme

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

@ThemePreviews
@Composable
private fun ProgressBarPreview() {
    FoodyTheme {
        ProgressBar()
    }
}
