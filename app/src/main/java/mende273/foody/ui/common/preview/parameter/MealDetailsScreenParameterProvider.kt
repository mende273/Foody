package mende273.foody.ui.common.preview.parameter

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import mende273.foody.domain.model.IngredientWithMeasure
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.common.preview.model.MealDetailsScreenPreviewModel
import mende273.foody.ui.state.UIState
import mende273.foody.ui.state.UIStateError

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MealDetailsScreenParameterProvider : PreviewParameterProvider<MealDetailsScreenPreviewModel> {

    private val mealDetails = MealDetails(
        id = 52874,
        name = "Beef and Mustard Pie",
        category = "Beef",
        area = "British",
        instructions = "Preheat the oven to 150C/300F/Gas 2.",
        ingredientsWithMeasures = listOf(
            IngredientWithMeasure("Beef", "1kg"),
            IngredientWithMeasure("Rapeseed Oil", "2 tbs"),
            IngredientWithMeasure("Egg Yolks", "2 free-range")
        ),
        source = "source url",
        youtube = "youtube url",
        tags = "beef,lunch",
        thumb = ""
    )

    override val values: Sequence<MealDetailsScreenPreviewModel> = sequenceOf(
        MealDetailsScreenPreviewModel(
            windowSizeClass = WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            ),
            uiState = UIState.Success(mealDetails),
            isFavourite = true
        ),
        MealDetailsScreenPreviewModel(
            windowSizeClass = WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            ),
            uiState = UIState.Error(UIStateError.GENERIC_ERROR),
            isFavourite = false
        ),
        MealDetailsScreenPreviewModel(
            windowSizeClass = WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            ),
            uiState = UIState.Loading,
            isFavourite = false
        )
    )
}
