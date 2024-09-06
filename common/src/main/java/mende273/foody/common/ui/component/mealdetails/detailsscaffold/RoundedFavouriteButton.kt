package mende273.foody.common.ui.component.mealdetails.detailsscaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.common.ui.component.RoundedComponent
import mende273.foody.common.ui.theme.FoodyTheme

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

@PreviewLightDark
@Composable
private fun RoundedFavouriteButtonWhenNotFavouritePreview() {
    FoodyTheme {
        RoundedFavouriteButton(
            isFavourite = false,
            onFavouriteClicked = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun RoundedFavouriteButtonWhenFavouritePreview() {
    FoodyTheme {
        RoundedFavouriteButton(
            isFavourite = true,
            onFavouriteClicked = {}
        )
    }
}
