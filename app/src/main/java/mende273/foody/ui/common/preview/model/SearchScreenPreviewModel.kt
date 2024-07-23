package mende273.foody.ui.common.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.domain.model.Meal
import mende273.foody.ui.state.UIState

class SearchScreenPreviewModel(val windowSize: WindowSizeClass, val uiState: UIState<List<Meal>>)
