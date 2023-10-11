package dev.mende273.foody.ui.screen.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FavouritesScreen(modifier: Modifier = Modifier, viewModel: FavouritesViewModel) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadData()
    })

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Favourites Screen")
    }
}
