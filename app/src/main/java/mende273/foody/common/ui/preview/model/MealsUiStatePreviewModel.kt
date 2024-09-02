package mende273.foody.common.ui.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.common.state.UIState
import mende273.foody.core.domain.model.Meal

class MealsUiStatePreviewModel(val windowSize: WindowSizeClass, val uiState: UIState<List<Meal>>)
