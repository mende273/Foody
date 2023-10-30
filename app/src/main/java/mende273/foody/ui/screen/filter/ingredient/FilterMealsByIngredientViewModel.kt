package mende273.foody.ui.screen.filter.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meals
import mende273.foody.domain.usecase.GetMealsWithIngredientUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

class FilterMealsByIngredientViewModel(
    private val getMealsWithIngredientUseCase: GetMealsWithIngredientUseCase
) : ViewModel() {

    private var _uiState: MutableStateFlow<UIState<Meals>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Meals>> = _uiState

    fun requestData(ingredient: String) {
        viewModelScope.launch {
            _uiState.value = getMealsWithIngredientUseCase(ingredient).toUIState()
        }
    }
}
