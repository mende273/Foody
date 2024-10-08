package mende273.foody.image

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

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String,
    withCrossFade: Boolean = false,
    error: Painter? = painterResource(id = R.drawable.image_placeholder),
    contentScale: NetworkImageContentScale = NetworkImageContentScale.NONE
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(withCrossFade)
            .build(),
        contentDescription = contentDescription,
        error = error,
        contentScale = getCoilContentScaleFor(contentScale),
        placeholder = painterResource(R.drawable.image_placeholder)
    )
}

@Preview
@Composable
private fun NetworkImagePreview() {
    NetworkImage(Modifier.fillMaxSize(), url = "", contentDescription = "")
}

private fun getCoilContentScaleFor(contentScale: NetworkImageContentScale): ContentScale =
    when (contentScale) {
        NetworkImageContentScale.NONE -> ContentScale.None
        NetworkImageContentScale.CROP -> ContentScale.Crop
        NetworkImageContentScale.FILL_HEIGHT -> ContentScale.FillHeight
        NetworkImageContentScale.INSIDE -> ContentScale.Inside
        NetworkImageContentScale.FIT -> ContentScale.Fit
    }
