package mende273.foody.ui.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.state.UIState

class MealDetailsScreenPreviewModel(
    val windowSizeClass: WindowSizeClass,
    val uiState: UIState<MealDetails>,
    val isFavourite: Boolean
)
