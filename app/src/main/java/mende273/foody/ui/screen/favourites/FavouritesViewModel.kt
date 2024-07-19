package mende273.foody.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meal
import mende273.foody.domain.usecase.GetAllFavoriteMealsFromDBUseCase
import mende273.foody.ui.state.UIState

class FavouritesViewModel(
    private val getAllFavoriteMealsFromDB: GetAllFavoriteMealsFromDBUseCase
) : ViewModel() {

    private val _meals = MutableStateFlow<UIState<List<Meal>>>(UIState.Loading)
    val meals: StateFlow<UIState<List<Meal>>> = _meals

    fun loadData() {
        viewModelScope.launch {
            val meals = getAllFavoriteMealsFromDB()
            when (meals.isNotEmpty()) {
                true -> _meals.update { UIState.Success(meals) }
                false -> _meals.update { UIState.Error("You don't have any favourite meals") }
            }
        }
    }
}
