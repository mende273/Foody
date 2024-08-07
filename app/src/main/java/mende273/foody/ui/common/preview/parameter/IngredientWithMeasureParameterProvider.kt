package mende273.foody.ui.common.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mende273.foody.domain.model.IngredientWithMeasure

class IngredientWithMeasureParameterProvider :
    PreviewParameterProvider<IngredientWithMeasure> {
    override val values: Sequence<IngredientWithMeasure> = sequenceOf(
        IngredientWithMeasure("Beef", "1kg"),
        IngredientWithMeasure("Rapeseed Oil", "2 tbs"),
        IngredientWithMeasure("Egg Yolks", "2 free-range")
    )
}
