package mende273.foody.ui.preview.parameter

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import mende273.foody.domain.model.Meal
import mende273.foody.ui.preview.model.MealsScreenPreviewModel
import mende273.foody.ui.state.Filter
import mende273.foody.ui.state.UIState

class MealsScreenParameterProvider : PreviewParameterProvider<MealsScreenPreviewModel> {

    private val currentFilterTabsUiState = UIState.Success(
        listOf(
            "Chicken",
            "Dessert",
            "Goat",
            "Lamb",
            "Pasta",
            "Pork"
        )
    )

    private val uiStateForCurrentTab = UIState.Success(
        listOf(
            Meal(id = 1, "Beef with cheese", ""),
            Meal(id = 1, "Beef and mustard pie", ""),
            Meal(id = 1, "Apple and blackberry crumble", ""),
            Meal(id = 1, "Banana pancakes", ""),
            Meal(id = 1, "Fettucine Alfredo", ""),
            Meal(id = 1, "Grilled mac and cheese sandwich", "")
        )
    )

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override val values: Sequence<MealsScreenPreviewModel> = sequenceOf(
        MealsScreenPreviewModel(
            currentFilterTabsUiState,
            uiStateForCurrentTab,
            Filter.CATEGORY,
            WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            )
        )
    )
}
