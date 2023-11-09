package mende273.foody.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mende273.foody.domain.model.Meal
import mende273.foody.ui.theme.NORMAL_PADDING
import mende273.foody.ui.theme.SMALL_PADDING

@Composable
fun MealsGrid(
    gridCellsCount: Int,
    meals: List<Meal>,
    onMealClicked: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = NORMAL_PADDING,
            end = NORMAL_PADDING
        ),
        columns = GridCells.Fixed(count = gridCellsCount),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
        horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(meals) { item ->
            MealItemComponent(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        onMealClicked(item.id.toString())
                    },
                meal = item,
                contentDescription = "meal item"
            )
        }
    }
}
