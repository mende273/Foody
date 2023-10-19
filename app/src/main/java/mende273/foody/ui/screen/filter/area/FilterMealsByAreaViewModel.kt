package mende273.foody.ui.screen.filter.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meals
import mende273.foody.domain.usecase.GetMealsForAreaUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.ERROR_LOADING_DATA

class FilterMealsByAreaViewModel(private val getMealsForAreaUseCase: GetMealsForAreaUseCase) :
    ViewModel() {

    private var _uiState: MutableStateFlow<UIState<Meals>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Meals>> = _uiState

    fun requestData(area: String) {
        viewModelScope.launch {
            _uiState.value = getMealsForAreaUseCase(area).fold(
                onSuccess = {
                    UIState.Success(it)
                },
                onFailure = {
                    UIState.Error(it.message ?: ERROR_LOADING_DATA)
                }
            )
        }
    }
}
