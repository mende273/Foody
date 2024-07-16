package mende273.foody.ui.component

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
import mende273.foody.R
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.theme.FoodyTheme

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

@ThemePreviews
@Composable
private fun TopBarPreview() {
    FoodyTheme {
        TopBar(title = stringResource(id = R.string.app_name))
    }
}

@ThemePreviews
@Composable
private fun TopBarWithBackEnabledPreview() {
    FoodyTheme {
        TopBar(title = stringResource(id = R.string.app_name), isBackButtonEnabled = true)
    }
}

@ThemePreviews
@Composable
private fun BackNavigationButtonPreview() {
    FoodyTheme {
        BackNavigationButton {}
    }
}
