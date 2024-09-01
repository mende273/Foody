package mende273.foody.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.usecase.local.GetAllFavoriteMealsFromDBUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.ui.state.UIStateError

class FavouritesViewModel(
    private val getAllFavoriteMealsFromDB: GetAllFavoriteMealsFromDBUseCase
) : ViewModel() {

    private val _meals = MutableStateFlow<UIState<List<Meal>>>(UIState.Loading)
    val meals: StateFlow<UIState<List<Meal>>> = _meals

    fun loadData() {
        viewModelScope.launch {
            val meals = getAllFavoriteMealsFromDB()
            when (meals.isNotEmpty()) {
                true -> updateUiState(UIState.Success(meals))
                false -> updateUiState(UIState.Error(UIStateError.NO_ITEMS))
            }
        }
    }

    private fun updateUiState(uiState: UIState<List<Meal>>) {
        viewModelScope.launch {
            _meals.update { uiState }
        }
    }
}
