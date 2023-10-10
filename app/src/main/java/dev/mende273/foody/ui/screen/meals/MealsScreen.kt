package dev.mende273.foody.ui.screen.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun MealsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.testTag("test_tag_meals_screen"),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Meals Screen")
    }
}
