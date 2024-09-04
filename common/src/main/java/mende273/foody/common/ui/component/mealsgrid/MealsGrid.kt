package mende273.foody.common.ui.component.mealsgrid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mende273.foody.common.ui.theme.spacing
import mende273.foody.core.domain.model.Meal

@Composable
fun MealsGrid(
    gridCellsCount: Int,
    meals: List<Meal>,
    onMealClicked: (Long) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.normal,
            end = MaterialTheme.spacing.normal
        ),
        columns = GridCells.Fixed(count = gridCellsCount),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(meals) { item ->
            MealItemComponent(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        onMealClicked(item.id)
                    },
                meal = item,
                contentDescription = "meal item"
            )
        }
    }
}
