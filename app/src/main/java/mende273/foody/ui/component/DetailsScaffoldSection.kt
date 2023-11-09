package mende273.foody.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.NORMAL_PADDING
import mende273.foody.ui.theme.largeTextStyle
import mende273.foody.ui.theme.mediumTextStyle

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
            Spacer(modifier = Modifier.height(NORMAL_PADDING))
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScaffoldSectionPreview() {
    FoodyTheme {
        Column {
            DetailsScaffoldSection(
                title = "title",
                isHeaderTitle = true,
                content = {
                    NormalText(
                        text = "contents",
                        textStyle = mediumTextStyle(),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }
    }
}
