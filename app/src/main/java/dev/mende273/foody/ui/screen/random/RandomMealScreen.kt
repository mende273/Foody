package dev.mende273.foody.ui.screen.random

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.mende273.foody.ui.state.UIState
import kotlinx.serialization.json.Json

@Composable
fun RandomMealScreen(modifier: Modifier = Modifier, viewModel: RandomMealViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.requestData()
    })

    when (uiState) {
        is UIState.Error -> Text(text = (uiState as UIState.Error).errorMessage)
        UIState.Loading -> Text(text = "loading")
        is UIState.Success -> {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                Text(text = "json: " + (uiState as UIState.Success<Json>).data)
            }
        }
    }
}
