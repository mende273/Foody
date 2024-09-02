package mende273.foody.common.ui.component.mealdetails.detailsscaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.common.ui.component.NormalText
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.largeTextStyle
import mende273.foody.common.ui.theme.mediumTextStyle
import mende273.foody.common.ui.theme.spacing

@Composable
fun ColumnScope.DetailsScaffoldSection(
    title: String? = null,
    isHeaderTitle: Boolean = false,
    addBottomSpace: Boolean = true,
    content: @Composable () -> Unit = {}
) {
    this.apply {
        if (!title.isNullOrEmpty()) {
            if (isHeaderTitle) {
                NormalText(text = title, textStyle = largeTextStyle())
            } else {
                NormalText(
                    text = title,
                    textStyle = mediumTextStyle(),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        content()
        if (addBottomSpace) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))
        }
    }
}

@PreviewLightDark
@PreviewFontScale
@Composable
private fun DetailsScaffoldSectionPreview() {
    FoodyTheme {
        Column {
            DetailsScaffoldSection(
                title = "title",
                isHeaderTitle = true,
                content = {
                    NormalText(
                        text = "composable contents",
                        textStyle = mediumTextStyle(),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }
    }
}
