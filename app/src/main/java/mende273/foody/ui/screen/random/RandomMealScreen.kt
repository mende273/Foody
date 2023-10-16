package mende273.foody.ui.screen.random

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.mende273.foody.R
import mende273.foody.domain.model.IngredientWithMeasure
import mende273.foody.domain.model.Meal
import mende273.foody.ui.component.DetailsComponent
import mende273.foody.ui.component.LargeText
import mende273.foody.ui.component.MediumText
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.component.SmallText
import mende273.foody.ui.state.UIState

@Composable
fun RandomMealScreen(
    modifier: Modifier = Modifier,
    viewModel: RandomMealViewModel,
    windowSize: WindowSizeClass
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.requestData()
    })

    when (uiState) {
        is UIState.Error -> Text(text = (uiState as UIState.Error).errorMessage)
        UIState.Loading -> ProgressBar(modifier)
        is UIState.Success -> Contents(
            modifier = modifier,
            meal = (uiState as UIState.Success<Meal>).data,
            windowSize = windowSize
        )
    }
}

const val WEIGHT = 0.3f

@Composable
private fun Contents(modifier: Modifier = Modifier, meal: Meal, windowSize: WindowSizeClass) {
    DetailsComponent(
        modifier = modifier.verticalScroll(rememberScrollState()),
        windowSize = windowSize,
        headerImageUrl = meal.thumb,
        onHeaderImageClicked = {
            // todo
        },
        contents = {
            Column(modifier) {
                LargeText(text = meal.name)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.small_padding)
                    )
                ) {
                    SmallText(text = meal.category)
                    SmallText(text = meal.area)
                }
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.normal_padding)))
                MediumText(text = "Instructions")
                MediumText(text = meal.instructions)

                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.normal_padding)))
                MediumText(text = "Ingredients")
                IngredientsWithMeasuresGrid(items = meal.ingredientsWithMeasures)

                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.normal_padding)))
                SmallText(text = meal.tags)
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IngredientsWithMeasuresGrid(items: List<IngredientWithMeasure>) {
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
            .fillMaxWidth(WEIGHT)
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
