package mende273.foody.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import mende273.foody.domain.model.IngredientWithMeasure
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.preview.parameter.IngredientWithMeasureParameterProvider
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.blackNormalTextStyle
import mende273.foody.ui.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IngredientsWithMeasuresGridComponent(
    items: List<IngredientWithMeasure>,
    onIngredientClicked: (String) -> Unit
) {
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
                modifier = itemModifier
                    .clickable { onIngredientClicked(items[index].ingredient) },
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
            .padding(MaterialTheme.spacing.smallSpacing),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = ingredientWithMeasure.ingredient,
                style = blackNormalTextStyle()
            )
            Text(
                textAlign = TextAlign.Center,
                text = ingredientWithMeasure.measure,
                style = blackNormalTextStyle()
            )
        }
    }
}

@ThemePreviews
@Composable
private fun IngredientWithMeasureItemPreview(
    @PreviewParameter(IngredientWithMeasureParameterProvider::class) item: IngredientWithMeasure
) {
    FoodyTheme {
        IngredientWithMeasureItem(
            Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .heightIn(min = 100.dp)
                .widthIn(min = 100.dp)
                .background(MaterialTheme.colorScheme.secondary),
            ingredientWithMeasure = item
        )
    }
}
