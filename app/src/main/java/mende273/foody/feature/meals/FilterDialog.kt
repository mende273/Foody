package mende273.foody.feature.meals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import mende273.foody.common.state.Filter
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.spacing

@Composable
fun FilterDialog(
    currentFilter: Filter,
    availableFilters: List<Filter>,
    onDismissRequest: () -> Unit,
    onFilterClicked: (Filter) -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 250.dp),
            shape = RoundedCornerShape(MaterialTheme.spacing.normal),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.normal)
            ) {
                availableFilters.forEach { filter ->
                    FilterDialogItem(
                        currentFilter = currentFilter,
                        filter = filter,
                        onFilterClicked = {
                            onFilterClicked(filter)
                        }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DialogPreview() {
    FoodyTheme {
        FilterDialog(
            currentFilter = Filter.CATEGORY,
            availableFilters = Filter.entries,
            onFilterClicked = {},
            onDismissRequest = {}
        )
    }
}
