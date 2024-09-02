package mende273.foody.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.common.state.UIState
import mende273.foody.common.ui.toUIState
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.usecase.remote.SearchMealsByNameUseCase

class SearchViewModel
(private val searchMealsByName: SearchMealsByNameUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<Meal>>>(
        UIState.Success(emptyList())
    )
    val uiState: StateFlow<UIState<List<Meal>>> = _uiState

    fun onSearchTextChange(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                _uiState.value = UIState.Loading
                _uiState.value = searchMealsByName(name).toUIState()
            }
        }
    }

    fun onClearSearch() {
        viewModelScope.launch {
            _uiState.value = UIState.Success(emptyList())
        }
    }
}
