package mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorComponent(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        LargeText(text = text)
    }
}