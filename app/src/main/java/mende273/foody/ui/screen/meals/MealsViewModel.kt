package mende273.foody.ui.screen.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.model.Meal
import mende273.foody.domain.usecase.FiltersWrapper
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import mende273.foody.ui.state.Filter
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

class MealsViewModel(
    private val remoteRepository: RemoteRepositoryImpl,
    private val getAllFiltersUseCase: GetAllFiltersUseCase
) : ViewModel() {

    init {
        fetchAllFilters()
    }

    private val allFiltersLoaded = MutableStateFlow(false)

    private val _headerTitle: MutableStateFlow<Filter> = MutableStateFlow(Filter.CATEGORY)
    val headerTitle: StateFlow<Filter> = _headerTitle

    private var allFilters: MutableStateFlow<FiltersWrapper> =
        MutableStateFlow(
            FiltersWrapper(
                categories = Result.success(emptyList()),
                areas = Result.success(emptyList()),
                firstLetters = emptyList()
            )
        )

    var currentFilter: StateFlow<UIState<List<String>>> =
        combine(allFilters, headerTitle, allFiltersLoaded) { filters, title, loaded ->
            when (loaded) {
                true ->
                    when (title) {
                        Filter.CATEGORY -> filters.categories.toUIState()
                        Filter.AREA -> filters.areas.toUIState()
                        Filter.FIRST_LETTER -> UIState.Success(filters.firstLetters)
                    }

                false -> UIState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UIState.Loading
        )

    private val _meals: MutableStateFlow<UIState<List<Meal>>> = MutableStateFlow(UIState.Loading)
    val meals: StateFlow<UIState<List<Meal>>> = _meals

    private fun fetchAllFilters() {
        viewModelScope.launch {
            getAllFiltersUseCase().collectLatest { remote ->
                allFilters.update { remote }

                _headerTitle.value = if (remote.categories.isSuccess) {
                    Filter.CATEGORY
                } else {
                    if (remote.areas.isSuccess) {
                        Filter.AREA
                    } else {
                        Filter.FIRST_LETTER
                    }
                }
                allFiltersLoaded.update { true }
            }
        }
    }

    fun loadFilter(filter: Filter) {
        viewModelScope.launch {
            _headerTitle.update { filter }
        }
    }

    fun fetchMeals(name: String) {
        viewModelScope.launch {
            _meals.value = when (_headerTitle.value) {
                Filter.CATEGORY -> remoteRepository.getMealsForCategory(name)
                Filter.AREA -> remoteRepository.getMealsForArea(name)
                Filter.FIRST_LETTER -> remoteRepository.getMealsForFirstLetter(name)
            }.toUIState()
        }
    }

    fun getAvailableFilters(): List<Filter> {
        val availableFilters = arrayListOf<Filter>()

        with(allFilters.value) {
            if (categories.isSuccess) {
                availableFilters.add(Filter.CATEGORY)
            }

            if (areas.isSuccess) {
                availableFilters.add(Filter.AREA)
            }

            if (firstLetters.isNotEmpty()) {
                availableFilters.add(Filter.FIRST_LETTER)
            }
        }

        return availableFilters
    }
}
