package mende273.foody.ui.state

sealed interface UIState<out T> {
    data class Success<T>(val data: T) : UIState<T>
    data class Error(val error: UIStateError) : UIState<Nothing>
    data object Loading : UIState<Nothing>
}
