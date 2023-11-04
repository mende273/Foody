package mende273.foody.ui.screen.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.usecase.GetRandomMealUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

class RandomMealViewModel(private val getRandomMealUseCase: GetRandomMealUseCase) : ViewModel() {

    private val _isFavourite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavourite: StateFlow<Boolean> = _isFavourite

    private val _uiState: MutableStateFlow<UIState<MealDetails>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<MealDetails>> = _uiState

    init {
        requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            _uiState.update {
                getRandomMealUseCase().toUIState()
            }
        }
    }

    fun toggleFavourite() {
        // TODO
    }
}
