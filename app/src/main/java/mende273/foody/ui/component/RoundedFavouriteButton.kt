package mende273.foody.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RoundedFavouriteButton(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit
) {
    RoundedComponent(modifier) {
        IconButton(onClick = { onFavouriteClicked() }) {
            val imageVector = when (isFavourite) {
                true -> Icons.Filled.Favorite
                false -> Icons.Filled.FavoriteBorder
            }
            Icon(imageVector, "favourite button")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundedFavouriteButtonWhenNotFavouritePreview() {
    RoundedFavouriteButton(
        isFavourite = false,
        onFavouriteClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RoundedFavouriteButtonWhenFavouritePreview() {
    RoundedFavouriteButton(
        isFavourite = true,
        onFavouriteClicked = {}
    )
}
