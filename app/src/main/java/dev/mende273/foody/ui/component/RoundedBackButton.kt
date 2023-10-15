package dev.mende273.foody.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.mende273.foody.R

@Composable
fun RoundedBackButton(navigateBackEvent: () -> Unit) {
    Box(
        Modifier.padding(
            start = dimensionResource(id = R.dimen.normal_padding),
            top = dimensionResource(id = R.dimen.large_padding)
        )
    ) {
        RoundedComponent {
            IconButton(onClick = { navigateBackEvent() }) {
                Icon(Icons.Filled.ArrowBack, "back button")
            }
        }
    }
}
