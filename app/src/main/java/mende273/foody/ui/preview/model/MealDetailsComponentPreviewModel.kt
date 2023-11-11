package mende273.foody.ui.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.domain.model.MealDetails

class MealDetailsComponentPreviewModel(
    val windowSizeClass: WindowSizeClass,
    val mealDetails: MealDetails,
    val isFavourite: Boolean
)
