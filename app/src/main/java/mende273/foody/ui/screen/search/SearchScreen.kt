package mende273.foody.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(modifier: Modifier = Modifier, viewModel: SearchViewModel) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadData()
    })
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Search Screen")
    }
}
