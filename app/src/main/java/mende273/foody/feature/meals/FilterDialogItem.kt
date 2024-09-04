package mende273.foody.feature.meals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import mende273.foody.common.ui.component.NormalText
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.mediumTextStyle

@Composable
fun FilterDialogItem(
    currentFilter: Filter,
    filter: Filter,
    onFilterClicked: (Filter) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = currentFilter.title == filter.title,
                onClick = {
                    onFilterClicked(filter)
                }
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.primary
            ),
            selected = currentFilter.title == filter.title,
            onClick = { onFilterClicked(filter) }
        )

        NormalText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = filter.title),
            textStyle = mediumTextStyle()
        )
    }
}

@PreviewLightDark
@Composable
private fun FilterDialogItemPreview() {
    FoodyTheme {
        FilterDialogItem(
            currentFilter = Filter.CATEGORY,
            filter = Filter.CATEGORY,
            onFilterClicked = {}
        )
    }
}
