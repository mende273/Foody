package mende273.foody.ui.screen.random

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import mende273.foody.R
import mende273.foody.domain.model.IngredientWithMeasure
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.component.DetailsComponent
import mende273.foody.ui.component.DetailsSection
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MediumText
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.component.SmallButton
import mende273.foody.ui.component.SmallText
import mende273.foody.ui.state.UIState

@Composable
fun RandomMealScreen(
    modifier: Modifier = Modifier,
    viewModel: RandomMealViewModel,
    windowSize: WindowSizeClass,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.requestData()
    })

    when (uiState) {
        is UIState.Error -> ErrorComponent(
            modifier = Modifier.fillMaxSize(),
            text = (uiState as UIState.Error).errorMessage
        )

        is UIState.Loading -> ProgressBar(Modifier.fillMaxSize())
        is UIState.Success -> MealDetails(
            modifier = modifier,
            mealDetails = (uiState as UIState.Success<MealDetails>).data,
            windowSize = windowSize,
            onHeaderImageClicked = { onHeaderImageClicked(it) },
            onCategoryClicked = { onCategoryClicked(it) },
            onAreaClicked = { onAreaClicked(it) },
            onVideoClicked = { onVideoClicked(it) },
            onSourceClicked = { onSourceClicked(it) }
        )
    }
}

@Composable
private fun MealDetails(
    modifier: Modifier = Modifier,
    mealDetails: MealDetails,
    windowSize: WindowSizeClass,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit
) {
    DetailsComponent(
        modifier = modifier.verticalScroll(rememberScrollState()),
        windowSize = windowSize,
        headerImageUrl = mealDetails.thumb,
        onHeaderImageClicked = { onHeaderImageClicked(it) },
        contents = {
            Column(modifier) {
                DetailsSection(
                    title = mealDetails.name,
                    isHeaderTitle = true,
                    addBottomSpace = false
                )

                DetailsSection(content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(id = R.dimen.small_padding)
                        )
                    ) {
                        if (mealDetails.category.isNotEmpty()) {
                            SmallButton(text = mealDetails.category, onClicked = {
                                onCategoryClicked(mealDetails.category)
                            })
                        }

                        if (mealDetails.area.isNotEmpty()) {
                            SmallButton(text = mealDetails.area, onClicked = {
                                onAreaClicked(mealDetails.area)
                            })
                        }
                    }
                })

                if (mealDetails.instructions.isNotEmpty()) {
                    DetailsSection(title = "Instructions", content = {
                        MediumText(text = mealDetails.instructions)
                    })
                }

                if (mealDetails.ingredientsWithMeasures.isNotEmpty()) {
                    DetailsSection(title = "Ingredients", content = {
                        IngredientsWithMeasuresGrid(items = mealDetails.ingredientsWithMeasures)
                    })
                }

                if (mealDetails.tags.isNotEmpty()) {
                    DetailsSection(title = "Tags", content = {
                        MediumText(text = mealDetails.tags)
                    })
                }

                if (mealDetails.youtube.isNotEmpty() || mealDetails.source.isNotEmpty()) {
                    DetailsSection(title = "More Info", content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                dimensionResource(id = R.dimen.normal_padding)
                            )
                        ) {
                            if (mealDetails.youtube.isNotEmpty()) {
                                SmallButton(text = "Video", onClicked = {
                                    onVideoClicked(mealDetails.youtube)
                                })
                            }

                            if (mealDetails.source.isNotEmpty()) {
                                SmallButton(text = "Source", onClicked = {
                                    onSourceClicked(mealDetails.source)
                                })
                            }
                        }
                    })
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
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
