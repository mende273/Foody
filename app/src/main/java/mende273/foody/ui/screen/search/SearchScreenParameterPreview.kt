package mende273.foody.ui.screen.search

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import mende273.foody.core.domain.model.Meal
import mende273.foody.ui.common.preview.model.SearchScreenPreviewModel
import mende273.foody.ui.state.UIState
import mende273.foody.ui.state.UIStateError

class SearchScreenParameterPreview : PreviewParameterProvider<SearchScreenPreviewModel> {

    private val items = listOf(
        Meal(id = 1, "Beef with cheese", ""),
        Meal(id = 1, "Beef and mustard pie", ""),
        Meal(id = 1, "Apple and blackberry crumble", ""),
        Meal(id = 1, "Banana pancakes", ""),
        Meal(id = 1, "Fettucine Alfredo", ""),
        Meal(id = 1, "Grilled mac and cheese sandwich", "")
    )

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override val values: Sequence<SearchScreenPreviewModel> = sequenceOf(
        SearchScreenPreviewModel(
            windowSize = WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            ),
            UIState.Success(items)
        ),
        SearchScreenPreviewModel(
            windowSize = WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            ),
            UIState.Error(UIStateError.GENERIC_ERROR)
        ),
        SearchScreenPreviewModel(
            windowSize = WindowSizeClass.calculateFromSize(
                DpSize(width = 673.5.dp, height = 841.dp)
            ),
            UIState.Loading
        )
    )
}
