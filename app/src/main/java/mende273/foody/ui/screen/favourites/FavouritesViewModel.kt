package mende273.foody.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.data.repository.LocalRepositoryImpl
import mende273.foody.domain.model.Meals
import mende273.foody.ui.state.UIState

class FavouritesViewModel(private val localRepository: LocalRepositoryImpl) : ViewModel() {

    private val _meals: MutableStateFlow<UIState<Meals>> = MutableStateFlow(UIState.Loading)
    val meals: StateFlow<UIState<Meals>> = _meals

    fun loadData() {
        viewModelScope.launch {
            _meals.value = UIState.Loading
            val meals = localRepository.getAllFavouriteMeals()
            if (meals.isNotEmpty()) {
                _meals.value = UIState.Success(Meals(localRepository.getAllFavouriteMeals()))
            } else {
                _meals.value = UIState.Error("You don't have any favourite meals")
            }
        }
    }
}
