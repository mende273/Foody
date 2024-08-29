package mende273.foody.ui.common.preview.model

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.core.domain.model.Meal
import mende273.foody.ui.state.Filter
import mende273.foody.ui.state.UIState

class MealsScreenPreviewModel(
    val currentFilterTabsUiState: UIState<List<String>>,
    val currentFilterTabItemsUiState: UIState<List<Meal>>,
    val currentFilter: Filter,
    val windowSize: WindowSizeClass
)
