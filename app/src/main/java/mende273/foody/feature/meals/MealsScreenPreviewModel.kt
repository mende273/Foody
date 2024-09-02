package mende273.foody.feature.meals

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import mende273.foody.common.state.Filter
import mende273.foody.common.state.UIState
import mende273.foody.core.domain.model.Meal

class MealsScreenPreviewModel(
    val currentFilterTabsUiState: UIState<List<String>>,
    val currentFilterTabItemsUiState: UIState<List<Meal>>,
    val currentFilter: Filter,
    val windowSize: WindowSizeClass
)
