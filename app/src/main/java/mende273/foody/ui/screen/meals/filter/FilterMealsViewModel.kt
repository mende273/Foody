package mende273.foody.ui.screen.meals.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meals
import mende273.foody.domain.usecase.GetMealsForAreaUseCase
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase
import mende273.foody.domain.usecase.GetMealsWithIngredientUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

class FilterMealsViewModel(
    private val getMealsForCategoryUseCase: GetMealsForCategoryUseCase,
    private val getMealsForAreaUseCase: GetMealsForAreaUseCase,
    private val getMealsWithIngredientUseCase: GetMealsWithIngredientUseCase
) : ViewModel() {

    private val _headerTitle: MutableStateFlow<Int?> = MutableStateFlow(null)
    val headerTitle: StateFlow<Int?> = _headerTitle

    private val _uiState: MutableStateFlow<UIState<Meals>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Meals>> = _uiState

    fun requestData(name: String, filterType: FilterType) {
        viewModelScope.launch {
            _headerTitle.value = filterType.title

            _uiState.value = when (filterType) {
                FilterType.CATEGORY -> getMealsForCategoryUseCase(name)
                FilterType.AREA -> getMealsForAreaUseCase(name)
                FilterType.INGREDIENT -> getMealsWithIngredientUseCase(name)
            }.toUIState()
        }
    }
}
