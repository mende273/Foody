package mende273.foody.common.ui.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.common.state.UIState
import mende273.foody.core.domain.model.MealDetails

class MealDetailsScreenPreviewModel(
    val windowSizeClass: WindowSizeClass,
    val uiState: UIState<MealDetails>,
    val isFavourite: Boolean
)
