package mende273.foody.ui.common.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.core.domain.model.MealDetails

class MealDetailsComponentPreviewModel(
    val windowSizeClass: WindowSizeClass,
    val mealDetails: MealDetails,
    val isFavourite: Boolean
)
