package mende273.foody.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meals
import mende273.foody.domain.usecase.SearchMealsByNameUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

class SearchViewModel
(private val searchMealsByNameUseCase: SearchMealsByNameUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<UIState<Meals>> =
        MutableStateFlow(UIState.Success(Meals(emptyList())))
    val uiState: StateFlow<UIState<Meals>> = _uiState

    fun onSearchTextChange(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                _uiState.value = UIState.Loading
                _uiState.value = searchMealsByNameUseCase(name).toUIState()
            }
        }
    }

    fun onClearSearch() {
        viewModelScope.launch {
            _uiState.value = UIState.Success(Meals(emptyList()))
        }
    }
}
