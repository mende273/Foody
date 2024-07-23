package mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.ui.state.UIState
import mende273.foody.ui.state.UIStateError
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.spacing

@Composable
fun <T> UiStateWrapper(
    uiState: UIState<T>,
    onError: @Composable (UIStateError) -> Unit = { error ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.normal),
            contentAlignment = Alignment.Center
        ) {
            ErrorComponent(text = stringResource(id = error.errorMessage))
        }
    },
    onLoading: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.normal),
            contentAlignment = Alignment.Center
        ) {
            ProgressBar()
        }
    },
    onSuccess: @Composable (T) -> Unit
) {
    when (uiState) {
        is UIState.Error -> onError(uiState.error)
        UIState.Loading -> onLoading()
        is UIState.Success -> onSuccess(uiState.data)
    }
}

@PreviewLightDark
@Composable
private fun UiStateWrapperLoadingPreview() {
    FoodyTheme {
        UiStateWrapper(uiState = UIState.Loading, onSuccess = {})
    }
}

@PreviewLightDark
@Composable
private fun UiStateWrapperErrorPreview() {
    FoodyTheme {
        UiStateWrapper(uiState = UIState.Error(UIStateError.GENERIC_ERROR), onSuccess = {})
    }
}

@PreviewLightDark
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
