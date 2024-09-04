package mende273.foody.common.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mende273.foody.core.domain.model.IngredientWithMeasure

class IngredientWithMeasureParameterProvider :
    PreviewParameterProvider<IngredientWithMeasure> {
    override val values: Sequence<IngredientWithMeasure> = sequenceOf(
        IngredientWithMeasure("Beef", "1kg"),
        IngredientWithMeasure("Rapeseed Oil", "2 tbs"),
        IngredientWithMeasure("Egg Yolks", "2 free-range")
    )
}
