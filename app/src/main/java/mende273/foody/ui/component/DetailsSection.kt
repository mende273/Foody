package mende273.foody.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import mende273.foody.R

@Composable
fun ColumnScope.DetailsSection(
    title: String? = null,
    isHeaderTitle: Boolean = false,
    addBottomSpace: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    this.apply {
        if (!title.isNullOrEmpty()) {
            if (isHeaderTitle) {
                LargeText(text = title)
            } else {
                MediumText(text = title, FontWeight.SemiBold)
            }
        }

        content()
        if (addBottomSpace) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_padding)))
        }
    }
}
