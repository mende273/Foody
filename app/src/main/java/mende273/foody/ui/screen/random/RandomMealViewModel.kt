package mende273.foody.ui.screen.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mende273.foody.data.mapper.toMeal
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.usecase.AddFavoriteMealToDBUseCase
import mende273.foody.domain.usecase.DeleteFavoriteMealFromDBUseCase
import mende273.foody.domain.usecase.GetFavoriteMealByIdFromDBUseCase
import mende273.foody.domain.usecase.GetRandomMealUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.ui.state.UIStateError

class RandomMealViewModel(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val addFavoriteMealToDB: AddFavoriteMealToDBUseCase,
    private val deleteFavoriteMealFromDB: DeleteFavoriteMealFromDBUseCase,
    private val getFavoriteMealByIdFromDB: GetFavoriteMealByIdFromDBUseCase
) : ViewModel() {

    private var meal: Meal? = null

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    private val _uiState = MutableStateFlow<UIState<MealDetails>>(UIState.Loading)
    val uiState: StateFlow<UIState<MealDetails>> get() = _uiState

    init {
        requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            getRandomMealUseCase().fold(
                onSuccess = { mealDetails ->
                    meal = mealDetails.toMeal()
                    updateUiState(UIState.Success(mealDetails))
                    checkIsFavorite(mealDetails.id)
                },
                onFailure = {
                    updateUiState(UIState.Error(UIStateError.GENERIC_ERROR))
                }
            )
        }
    }

    private fun updateUiState(uiState: UIState<MealDetails>) {
        viewModelScope.launch {
            _uiState.update { uiState }
        }
    }

    private fun checkIsFavorite(id: Long) {
        viewModelScope.launch {
            getFavoriteMealByIdFromDB(id).collectLatest { meal ->
                _isFavorite.update { meal != null }
            }
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            meal?.let {
                when (_isFavorite.value) {
                    true -> deleteFavoriteMealFromDB(it)
                    false -> addFavoriteMealToDB(it)
                }
            }
        }
    }
}
