package mende273.foody.ui.screen.filter.category

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mende273.foody.ui.component.LargeText

@Composable
fun FilterMealsByCategory(
    modifier: Modifier = Modifier,
    viewModel: FilterMealByCategoryViewModel,
    categoryName: String
) {
    LaunchedEffect(key1 = categoryName, block = {
        viewModel.requestData(categoryName)
    })

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        LargeText(text = "Filter meals by category: $categoryName")
    }
}
