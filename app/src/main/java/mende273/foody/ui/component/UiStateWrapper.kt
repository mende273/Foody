package mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.state.UIState
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.util.ERROR_LOADING_DATA

@Composable
fun <T> UiStateWrapper(
    uiState: UIState<T>,
    onError: @Composable (String) -> Unit = { errorMessage ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ErrorComponent(text = errorMessage)
        }
    },
    onLoading: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ProgressBar()
        }
    },
    onSuccess: @Composable (T) -> Unit
) {
    when (uiState) {
        is UIState.Error -> onError(uiState.errorMessage)
        UIState.Loading -> onLoading()
        is UIState.Success -> onSuccess(uiState.data)
    }
}

@ThemePreviews
@Composable
private fun UiStateWrapperLoadingPreview() {
    FoodyTheme {
        UiStateWrapper(uiState = UIState.Loading, onSuccess = {})
    }
}

@ThemePreviews
@Composable
private fun UiStateWrapperErrorPreview() {
    FoodyTheme {
        UiStateWrapper(uiState = UIState.Error(ERROR_LOADING_DATA), onSuccess = {})
    }
}

@ThemePreviews
@Composable
private fun UiStateWrapperSuccessPreview() {
    FoodyTheme {
        UiStateWrapper(
            uiState = UIState.Success("contents go here"),
            onSuccess = { data ->
                Box(modifier = Modifier.fillMaxSize()) {
                    NormalText(text = data)
                }
            }
        )
    }
}
