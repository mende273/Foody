package mende273.foody.ui.screen.meals.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meal
import mende273.foody.domain.usecase.GetMealsForAreaUseCase
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase
import mende273.foody.domain.usecase.GetMealsWithIngredientUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

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
