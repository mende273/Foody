package mende273.foody.util

import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mende273.foody.ui.state.UIState
import org.junit.Test

@ExperimentalCoroutinesApi
class ResultToUIStateTest {

    @Test
    fun `toUIState should convert success result to Success UIState`() = runTest {
        val successResult = Result.success("Success data")
        val uiState = successResult.toUIState()

        assertEquals(UIState.Success("Success data"), uiState)
    }

    @Test
    fun `toUIState should convert failure result to Error UIState with error message`() = runTest {
        val failureResult = Result.failure<Any>(Exception("Error loading data"))
        val uiState = failureResult.toUIState()

        assertEquals(UIState.Error("Error loading data"), uiState)
    }

    @Test
    fun `toUIState should convert failure result to Error UIState `() = runTest {
        val failureResult = Result.failure<Any>(Exception())
        val uiState = failureResult.toUIState()

        assertEquals(UIState.Error(ERROR_LOADING_DATA), uiState)
    }
}
