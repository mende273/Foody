package mende273.foody.common.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mende273.foody.R
import mende273.foody.common.ui.theme.FoodyTheme

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
        contentScale = contentScale,
        placeholder = painterResource(R.drawable.image_placeholder)
    )
}

@Preview
@Composable
private fun NetworkImagePreview() {
    FoodyTheme {
        NetworkImage(Modifier.fillMaxSize(), url = "", contentDescription = "")
    }
}
