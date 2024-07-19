package mende273.foody.ui.screen.meals.details

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
import mende273.foody.domain.usecase.GetMealDetailsUseCase
import mende273.foody.ui.state.UIState

class MealDetailsViewModel(
    private val getFavoriteMealByIdFromDB: GetFavoriteMealByIdFromDBUseCase,
    private val deleteFavoriteMealFromDB: DeleteFavoriteMealFromDBUseCase,
    private val addFavoriteMealToDB: AddFavoriteMealToDBUseCase,
    private val getMealDetails: GetMealDetailsUseCase
) : ViewModel() {

    private var meal: Meal? = null

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    private val _uiState = MutableStateFlow<UIState<MealDetails>>(UIState.Loading)
    val uiState: StateFlow<UIState<MealDetails>> = _uiState

    fun init(id: Long) {
        checkIsFavorite(id)
        fetchMealDetails(id)
    }

    private fun fetchMealDetails(id: Long) {
        viewModelScope.launch {
            getMealDetails(id).fold(
                onSuccess = { mealDetails ->
                    meal = mealDetails.toMeal()
                    updateUiState(UIState.Success(mealDetails))
                },
                onFailure = {
                    updateUiState(UIState.Error(""))
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
            getFavoriteMealByIdFromDB(id)
                .collectLatest { meal ->
                    _isFavorite.update { meal != null }
                }
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            meal?.let {
                when (isFavorite.value) {
                    true -> deleteFavoriteMealFromDB(it)

                    false -> addFavoriteMealToDB(it)
                }
            }
        }
    }
}
