package mende273.foody.feature.filtermeals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.common.state.UIState
import mende273.foody.common.ui.toUIState
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.usecase.remote.GetMealsForAreaUseCase
import mende273.foody.core.domain.usecase.remote.GetMealsForCategoryUseCase
import mende273.foody.core.domain.usecase.remote.GetMealsWithIngredientUseCase

class FilterMealsViewModel(
    private val getMealsForCategory: GetMealsForCategoryUseCase,
    private val getMealsForArea: GetMealsForAreaUseCase,
    private val getMealsWithIngredient: GetMealsWithIngredientUseCase
) : ViewModel() {

    private val _headerTitle: MutableStateFlow<Int?> = MutableStateFlow(null)
    val headerTitle: StateFlow<Int?> = _headerTitle

    private val _uiState: MutableStateFlow<UIState<List<Meal>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Meal>>> = _uiState

    fun requestData(name: String, filterType: FilterType) {
        viewModelScope.launch {
            _headerTitle.value = filterType.title

            _uiState.value = when (filterType) {
                FilterType.CATEGORY -> getMealsForCategory(name)
                FilterType.AREA -> getMealsForArea(name)
                FilterType.INGREDIENT -> getMealsWithIngredient(name)
            }.toUIState()
        }
    }
}
