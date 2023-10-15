package dev.mende273.foody.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String,
    withCrossFade: Boolean = false,
    error: Painter? = null,
    contentScale: ContentScale = ContentScale.None
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(withCrossFade)
            .build(),
        contentDescription = contentDescription,
        error = error,
        contentScale = contentScale
    )
}
