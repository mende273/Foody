package mende273.foody.ui.screen.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meals
import mende273.foody.domain.usecase.GetMealCategoriesUseCase
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.ERROR_LOADING_DATA

class MealsViewModel(
    private val getMealCategoriesUseCase: GetMealCategoriesUseCase,
    private val getMealsForCategoryUseCase: GetMealsForCategoryUseCase
) : ViewModel() {

    private var _uiStateCategories: MutableStateFlow<UIState<List<String>>> =
        MutableStateFlow(UIState.Loading)
    val uiStateCategories: StateFlow<UIState<List<String>>> = _uiStateCategories

    init {
        loadCategories()
    }

    private var _uiStateCategoryMeals: MutableStateFlow<UIState<Meals>> =
        MutableStateFlow(UIState.Loading)
    val uiStateCategoryMeals: StateFlow<UIState<Meals>> = _uiStateCategoryMeals

    private fun loadCategories() {
        viewModelScope.launch {
            _uiStateCategories.value = getMealCategoriesUseCase().fold(
                onSuccess = {
                    UIState.Success(it)
                },
                onFailure = {
                    UIState.Error(it.message ?: ERROR_LOADING_DATA)
                }
            )
        }
    }

    fun loadCategoryData(category: String) {
        viewModelScope.launch {
            _uiStateCategoryMeals.value = getMealsForCategoryUseCase(category).fold(
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
