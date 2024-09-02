package mende273.foody.common.ui.component.mealdetails

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import mende273.foody.core.domain.model.IngredientWithMeasure
import mende273.foody.core.domain.model.MealDetails

class MealDetailsComponentParameterProvider :
    PreviewParameterProvider<MealDetailsComponentPreviewModel> {

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

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override val values: Sequence<MealDetailsComponentPreviewModel> =
        sequenceOf(
            MealDetailsComponentPreviewModel(
                windowSizeClass = WindowSizeClass.calculateFromSize(
                    DpSize(width = 673.5.dp, height = 841.dp)
                ),
                mealDetails = mealDetails,
                isFavourite = true
            ),
            MealDetailsComponentPreviewModel(
                windowSizeClass = WindowSizeClass.calculateFromSize(
                    DpSize(width = 1920.dp, height = 1080.dp)
                ),
                mealDetails = mealDetails,
                isFavourite = false
            )
        )
}
