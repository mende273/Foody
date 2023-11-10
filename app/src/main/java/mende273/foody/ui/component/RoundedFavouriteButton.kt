package mende273.foody.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mende273.foody.ui.annotations.ThemePreviews
import mende273.foody.ui.theme.FoodyTheme

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

@ThemePreviews
@Composable
private fun RoundedFavouriteButtonWhenNotFavouritePreview() {
    FoodyTheme {
        RoundedFavouriteButton(
            isFavourite = false,
            onFavouriteClicked = {}
        )
    }
}

@ThemePreviews
@Composable
private fun RoundedFavouriteButtonWhenFavouritePreview() {
    FoodyTheme {
        RoundedFavouriteButton(
            isFavourite = true,
            onFavouriteClicked = {}
        )
    }
}
