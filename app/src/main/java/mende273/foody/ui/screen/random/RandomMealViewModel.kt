package mende273.foody.ui.screen.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.usecase.GetRandomMealUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.ERROR_LOADING_DATA

class RandomMealViewModel(private val getRandomMealUseCase: GetRandomMealUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<UIState<MealDetails>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<MealDetails>> = _uiState

    fun requestData() {
        viewModelScope.launch {
            _uiState.value = getRandomMealUseCase().fold(
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
