package mende273.foody.util

import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mende273.foody.common.state.UIState
import mende273.foody.common.state.UIStateError
import mende273.foody.common.toUIState
import org.junit.Test

@ExperimentalCoroutinesApi
class ResultToUIStateTest {

    @Test
    fun `toUIState should convert success result to Success UIState`() {
        val successResult = Result.success("Success data")
        val uiState = successResult.toUIState()

        assertEquals(UIState.Success("Success data"), uiState)
    }

    @Test
    fun `toUIState should convert failure result to Error UIState with error message`() {
        val failureResult = Result.failure<Any>(Exception("Error loading data"))
        val uiState = failureResult.toUIState()

        assertEquals(UIState.Error(UIStateError.GENERIC_ERROR), uiState)
    }
}
