package mende273.foody.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import dev.mende273.foody.R

@Composable
fun DetailsComponent(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    headerImageUrl: String,
    isBackButtonEnabled: Boolean = false,
    onHeaderImageClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit = {},
    contents: @Composable () -> Unit
) {
    val headerImageModifier = Modifier
        .headerImage(windowSize)
        .clickable {
            if (headerImageUrl.isNotEmpty()) {
                onHeaderImageClicked(headerImageUrl)
            }
        }

    when (
        windowSize.widthSizeClass <= WindowWidthSizeClass.Medium &&
            windowSize.heightSizeClass >= WindowHeightSizeClass.Medium
    ) {
        true -> PortraitDetailsComponent(
            modifier = modifier,
            headerImageModifier = headerImageModifier,
            headerImageUrl = headerImageUrl,
            isBackButtonEnabled = isBackButtonEnabled,
            onNavigateBackClicked = { onNavigateBackClicked() },
            contents = { contents() }
        )

        false -> LandscapeDetailsComponent(
            modifier = modifier,
            headerImageModifier = headerImageModifier,
            headerImageUrl = headerImageUrl,
            isBackButtonEnabled = isBackButtonEnabled,
            onNavigateBackClicked = { onNavigateBackClicked() },
            contents = { contents() }
        )
    }
}

@Composable
private fun PortraitDetailsComponent(
    modifier: Modifier,
    headerImageModifier: Modifier,
    headerImageUrl: String,
    isBackButtonEnabled: Boolean,
    onNavigateBackClicked: () -> Unit,
    contents: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.negative_space_between)
        )
    ) {
        Box {
            NetworkImage(
                modifier = headerImageModifier,
                url = headerImageUrl,
                contentDescription = "meal image",
                contentScale = ContentScale.Crop
            )

            if (isBackButtonEnabled) {
                RoundedBackButton(navigateBackEvent = { onNavigateBackClicked() })
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                corner = CornerSize(dimensionResource(id = R.dimen.space_between))
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Box(modifier = Modifier.padding(dimensionResource(id = R.dimen.normal_padding))) {
                contents()
            }
        }
    }
}

@Composable
private fun LandscapeDetailsComponent(
    modifier: Modifier,
    headerImageModifier: Modifier,
    headerImageUrl: String,
    isBackButtonEnabled: Boolean,
    onNavigateBackClicked: () -> Unit,
    contents: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.negative_space_between)
        )
    ) {
        Box {
            NetworkImage(
                modifier = headerImageModifier,
                url = headerImageUrl,
                contentDescription = "meal image",
                contentScale = ContentScale.FillHeight
            )

            if (isBackButtonEnabled) {
                RoundedBackButton(navigateBackEvent = { onNavigateBackClicked() })
            }
        }

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(
                corner = CornerSize(dimensionResource(id = R.dimen.space_between))
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Box(modifier = modifier.padding(dimensionResource(id = R.dimen.normal_padding))) {
                contents()
            }
        }
    }
}

private fun Modifier.headerImage(windowSize: WindowSizeClass): Modifier {
    return if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
        this.fillMaxWidth()
    } else if (windowSize.widthSizeClass == WindowWidthSizeClass.Medium &&
        windowSize.heightSizeClass == WindowHeightSizeClass.Expanded
    ) {
        this
            .fillMaxWidth()
            .heightIn(max = 600.dp)
    } else if (windowSize.widthSizeClass == WindowWidthSizeClass.Medium &&
        windowSize.heightSizeClass == WindowHeightSizeClass.Medium
    ) {
        this
            .fillMaxWidth()
            .heightIn(max = 450.dp)
    } else {
        this
            .fillMaxHeight()
            .widthIn(max = 300.dp)
    }
}
