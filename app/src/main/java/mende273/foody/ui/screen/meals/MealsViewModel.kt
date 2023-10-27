package mende273.foody.ui.screen.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mende273.foody.domain.model.Meals
import mende273.foody.domain.usecase.FiltersWrapper
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import mende273.foody.domain.usecase.GetMealsForAreaUseCase
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase
import mende273.foody.domain.usecase.GetMealsForFirstLetterUseCase
import mende273.foody.ui.state.UIState
import mende273.foody.util.ERROR_LOADING_DATA
import mende273.foody.util.toUIState

class MealsViewModel(
    private val getMealsForCategoryUseCase: GetMealsForCategoryUseCase,
    private val getMealsForAreaUseCase: GetMealsForAreaUseCase,
    private val getMealsForFirstLetterUseCase: GetMealsForFirstLetterUseCase,
    private val getAllFiltersUseCase: GetAllFiltersUseCase
) : ViewModel() {

    init {
        fetchAllFilters()
    }

    private val _headerTitle: MutableStateFlow<Filter> = MutableStateFlow(Filter.CATEGORY)
    val headerTitle: StateFlow<Filter> = _headerTitle

    private var _filters: MutableStateFlow<UIState<FiltersWrapper>> =
        MutableStateFlow(UIState.Loading)
    val filters: StateFlow<UIState<FiltersWrapper>> = _filters

    var currentFilter: StateFlow<UIState<List<String>>> =
        _filters.combine(headerTitle) { filters, title ->
            when (filters) {
                is UIState.Error -> UIState.Error(ERROR_LOADING_DATA)
                UIState.Loading -> UIState.Loading
                is UIState.Success -> {
                    when (title) {
                        Filter.CATEGORY -> filters.data.categories.toUIState()
                        Filter.AREA -> filters.data.areas.toUIState()
                        Filter.FIRST_LETTER -> UIState.Success(filters.data.firstLetters)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UIState.Loading
        )

    private val _meals: MutableStateFlow<UIState<Meals>> = MutableStateFlow(UIState.Loading)
    val meals: StateFlow<UIState<Meals>> = _meals

    private fun fetchAllFilters() {
        viewModelScope.launch {
            getAllFiltersUseCase().collectLatest { remote ->
                _filters.value = UIState.Success(remote)
            }
        }
    }

    fun loadFilter(filter: Filter) {
        viewModelScope.launch {
            _headerTitle.value = filter
        }
    }

    fun fetchMeals(name: String) {
        viewModelScope.launch {
            _meals.value = when (_headerTitle.value) {
                Filter.CATEGORY -> getMealsForCategoryUseCase(name).toUIState()
                Filter.AREA -> getMealsForAreaUseCase(name).toUIState()
                Filter.FIRST_LETTER -> getMealsForFirstLetterUseCase(name).toUIState()
            }
        }
    }
}
