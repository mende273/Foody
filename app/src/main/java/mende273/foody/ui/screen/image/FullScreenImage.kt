package mende273.foody.ui.screen.image

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import mende273.foody.ui.component.NetworkImage
import mende273.foody.ui.component.RoundedBackButton

@Composable
fun FullScreenImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onNavigateBackClicked: () -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    Box {
        NetworkImage(
            modifier = modifier
                .blur(
                    radiusX = 50.dp,
                    radiusY = 50.dp
                ),
            url = imageUrl,
            contentDescription = "background blurred image",
            contentScale = ContentScale.Crop
        )
        NetworkImage(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .transformable(state = state)
                .fillMaxSize(),
            url = imageUrl,
            contentDescription = "full screen meal image",
            contentScale = ContentScale.Fit
        )
        RoundedBackButton(navigateBackEvent = { onNavigateBackClicked() })
    }
}
