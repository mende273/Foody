package mende273.foody.ui.component

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.mende273.foody.R

@Composable
fun RoundedComponent(
    modifier: Modifier = Modifier,
    contents: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            corner = CornerSize(dimensionResource(id = R.dimen.small_padding))
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        contents()
    }
}

@Preview
@Composable
fun RoundedComponentPreview() {
    RoundedComponent {
        Text(text = stringResource(id = R.string.app_name))
    }
}
