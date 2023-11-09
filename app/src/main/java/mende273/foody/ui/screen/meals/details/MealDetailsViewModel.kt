package mende273.foody.ui.screen.meals.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mende273.foody.data.repository.LocalRepositoryImpl
import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.MealDetails
import mende273.foody.ui.state.UIState
import mende273.foody.util.toUIState

@OptIn(ExperimentalCoroutinesApi::class)
class MealDetailsViewModel(
    private val remoteRepository: RemoteRepositoryImpl,
    private val localRepository: LocalRepositoryImpl
) : ViewModel() {

    private val _mealFromLocalDb = MutableStateFlow<Flow<Meal?>>(emptyFlow())
    val mealFromLocalDb: StateFlow<Meal?> = _mealFromLocalDb.flatMapLatest {
        it
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        null
    )

    private var mealDetailsResult: Result<MealDetails?> = Result.success(null)

    private val _uiState: MutableStateFlow<UIState<MealDetails>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<MealDetails>> = _uiState

    fun requestData(id: String) {
        viewModelScope.launch {
            _mealFromLocalDb.value = localRepository.getFavouriteMealById(id.toLong())

            with(remoteRepository.getMealDetails(id)) {
                mealDetailsResult = this
                _uiState.value = this.toUIState()
            }
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            mealFromLocalDb.value?.let {
                localRepository.deleteFavouriteMeal(meal = it)
            } ?: run {
                mealDetailsResult.getOrNull()?.let {
                    localRepository.addFavouriteMeal(Meal(it.id, it.name, it.thumb))
                }
            }
        }
    }
}
