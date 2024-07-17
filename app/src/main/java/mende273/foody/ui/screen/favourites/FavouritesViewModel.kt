package mende273.foody.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.LocalRepository
import mende273.foody.ui.state.UIState

class FavouritesViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val _meals: MutableStateFlow<UIState<List<Meal>>> = MutableStateFlow(UIState.Loading)
    val meals: StateFlow<UIState<List<Meal>>> = _meals

    fun loadData() {
        viewModelScope.launch {
            _meals.value = UIState.Loading
            val meals = localRepository.getAllFavouriteMeals()
            if (meals.isNotEmpty()) {
                _meals.value = UIState.Success(localRepository.getAllFavouriteMeals())
            } else {
                _meals.value = UIState.Error("You don't have any favourite meals")
            }
        }
    }
}
