package mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.LARGE_PADDING
import mende273.foody.ui.theme.NORMAL_PADDING

@Composable
fun RoundedBackButton(onNavigateBackClicked: () -> Unit) {
    Box(
        Modifier.padding(
            start = NORMAL_PADDING,
            top = LARGE_PADDING
        )
    ) {
        RoundedComponent {
            IconButton(onClick = { onNavigateBackClicked() }) {
                Icon(Icons.Filled.ArrowBack, "back button")
            }
        }
    }
}

@ThemePreviews
@Composable
private fun RoundedBackButtonPreview() {
    FoodyTheme {
        RoundedBackButton(onNavigateBackClicked = {})
    }
}
