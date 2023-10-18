package mende273.foody.ui.screen.filter.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mende273.foody.R
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.Meals
import mende273.foody.ui.component.ErrorComponent
import mende273.foody.ui.component.MediumText
import mende273.foody.ui.component.NetworkImage
import mende273.foody.ui.component.ProgressBar
import mende273.foody.ui.component.RoundedComponent
import mende273.foody.ui.component.TopBar
import mende273.foody.ui.state.UIState
import mende273.foody.util.GRID_CELLS_COUNT_IN_LANDSCAPE
import mende273.foody.util.GRID_CELLS_COUNT_IN_PORTRAIT

@Composable
fun FilterMealsByCategory(
    modifier: Modifier = Modifier,
    viewModel: FilterMealByCategoryViewModel,
    windowSize: WindowSizeClass,
    category: String,
    onMealClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = category, block = {
        viewModel.requestData(category)
    })

    Column(modifier = modifier) {
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "Category: $category",
            isBackButtonEnabled = true,
            onNavigateBackClicked = { onNavigateBackClicked() }
        )

        when (uiState) {
            is UIState.Error -> ErrorComponent(
                modifier = modifier,
                text = (uiState as UIState.Error).errorMessage
            )

            is UIState.Loading -> ProgressBar(modifier)
            is UIState.Success -> {
                val gridCells = if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                    GRID_CELLS_COUNT_IN_PORTRAIT
                } else {
                    GRID_CELLS_COUNT_IN_LANDSCAPE
                }

                MealsGrid(
                    gridCellsCount = gridCells,
                    meals = (uiState as UIState.Success<Meals>).data.meals,
                    onMealClicked = {
                        onMealClicked(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun MealsGrid(
    gridCellsCount: Int,
    meals: List<Meal>,
    onMealClicked: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.normal_padding),
            end = dimensionResource(id = R.dimen.normal_padding)
        ),
        columns = GridCells.Fixed(count = gridCellsCount),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_padding))
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

@Composable
fun MealItemComponent(
    modifier: Modifier = Modifier,
    meal: Meal,
    contentDescription: String
) {
    Column(modifier) {
        RoundedComponent {
            NetworkImage(
                url = meal.thumb,
                contentDescription = contentDescription,
                withCrossFade = true,
                contentScale = ContentScale.Inside,
                error = painterResource(R.drawable.image_placeholder)
            )
        }
        if (meal.name.isNotEmpty()) {
            MediumText(text = meal.name)
        }
    }
}
