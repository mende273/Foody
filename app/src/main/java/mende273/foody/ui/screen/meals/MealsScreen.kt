package mende273.foody.ui.screen.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun MealsScreen(modifier: Modifier = Modifier, viewModel: MealsViewModel) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadData()
    })

    Box(
        modifier = modifier.testTag("test_tag_meals_screen"),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Meals Screen")
    }
}
