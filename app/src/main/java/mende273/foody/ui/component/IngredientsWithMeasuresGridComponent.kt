package mende273.foody.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mende273.foody.R
import mende273.foody.domain.model.IngredientWithMeasure

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IngredientsWithMeasuresGridComponent(items: List<IngredientWithMeasure>) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = 3
    ) {
        val itemModifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .heightIn(min = 100.dp)
            .background(MaterialTheme.colorScheme.secondary)
        repeat(items.size) { index ->
            IngredientWithMeasureItem(
                modifier = itemModifier,
                ingredientWithMeasure = items[index]
            )
        }
    }
}

@Composable
private fun IngredientWithMeasureItem(
    modifier: Modifier = Modifier,
    ingredientWithMeasure: IngredientWithMeasure
) {
    Box(
        modifier = modifier
            .fillMaxWidth(fraction = 0.3f)
            .padding(dimensionResource(id = R.dimen.small_padding)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SmallText(
                textAlign = TextAlign.Center,
                text = ingredientWithMeasure.ingredient
            )
            SmallText(
                textAlign = TextAlign.Center,
                text = ingredientWithMeasure.measure
            )
        }
    }
}