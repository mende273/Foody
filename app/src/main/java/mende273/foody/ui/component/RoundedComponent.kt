package mende273.foody.ui.component

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import mende273.foody.R
import mende273.foody.ui.annotations.ThemePreviews
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.SMALL_PADDING

@Composable
fun RoundedComponent(
    modifier: Modifier = Modifier,
    contents: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            corner = CornerSize(SMALL_PADDING)
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        contents()
    }
}

@ThemePreviews
@Composable
fun RoundedComponentPreview() {
    FoodyTheme {
        RoundedComponent {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}
