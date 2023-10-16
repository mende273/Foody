package dev.mende273.foody.ui.screen.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mende273.foody.domain.model.Meal
import dev.mende273.foody.domain.usecase.GetRandomMealUseCase
import dev.mende273.foody.ui.state.UIState
import dev.mende273.foody.util.exception.ERROR_LOADING_DATA
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomMealViewModel(private val getRandomMealUseCase: GetRandomMealUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<UIState<Meal>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Meal>> = _uiState

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
