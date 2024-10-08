package mende273.foody.common.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.common.R
import mende273.foody.common.ui.theme.FoodyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    isBackButtonEnabled: Boolean = false,
    onNavigateBackClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (isBackButtonEnabled) {
                BackNavigationButton(navigateBackEvent = { onNavigateBackClicked() })
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun BackNavigationButton(navigateBackEvent: () -> Unit) {
    IconButton(onClick = { navigateBackEvent() }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "navigate back"
        )
    }
}

@PreviewLightDark
@Composable
private fun TopBarPreview() {
    FoodyTheme {
        TopBar(title = stringResource(id = R.string.placeholder_text))
    }
}

@PreviewLightDark
@Composable
private fun TopBarWithBackEnabledPreview() {
    FoodyTheme {
        TopBar(title = stringResource(id = R.string.placeholder_text), isBackButtonEnabled = true)
    }
}

@PreviewLightDark
@Composable
private fun BackNavigationButtonPreview() {
    FoodyTheme {
        BackNavigationButton {}
    }
}
