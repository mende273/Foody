package mende273.foody.common.ui.component.mealdetails.detailsscaffold

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import mende273.foody.common.ui.component.NormalText
import mende273.foody.common.ui.component.RoundedBackButton
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.spacing
import mende273.foody.image.NetworkImage
import mende273.foody.image.NetworkImageContentScale

private val spaceBetween = 25.dp
private val negativeSpaceBetween = -spaceBetween

@Composable
fun DetailsScaffold(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    headerImageUrl: String,
    isBackButtonEnabled: Boolean = false,
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit,
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
            isFavourite = isFavourite,
            onFavouriteClicked = { onFavouriteClicked() },
            onNavigateBackClicked = { onNavigateBackClicked() },
            contents = { contents() }
        )

        false -> LandscapeDetailsComponent(
            modifier = modifier,
            headerImageModifier = headerImageModifier,
            headerImageUrl = headerImageUrl,
            isBackButtonEnabled = isBackButtonEnabled,
            isFavourite = isFavourite,
            onFavouriteClicked = { onFavouriteClicked() },
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
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit,
    onNavigateBackClicked: () -> Unit,
    contents: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(negativeSpaceBetween)
    ) {
        Box(Modifier.heightIn(min = 300.dp)) {
            NetworkImage(
                modifier = headerImageModifier,
                url = headerImageUrl,
                contentDescription = "meal image",
                contentScale = NetworkImageContentScale.CROP
            )

            if (isBackButtonEnabled) {
                RoundedBackButton(onNavigateBackClicked = { onNavigateBackClicked() })
            }

            RoundedFavouriteButton(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        end = MaterialTheme.spacing.normal,
                        top = MaterialTheme.spacing.large
                    ),
                isFavourite = isFavourite,
                onFavouriteClicked = { onFavouriteClicked() }
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                corner = CornerSize(spaceBetween)
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Box(modifier = Modifier.padding(MaterialTheme.spacing.normal)) {
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
    isFavourite: Boolean,
    onNavigateBackClicked: () -> Unit,
    onFavouriteClicked: () -> Unit,
    contents: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(negativeSpaceBetween)
    ) {
        Box {
            NetworkImage(
                modifier = headerImageModifier,
                url = headerImageUrl,
                contentDescription = "meal image",
                contentScale = NetworkImageContentScale.FILL_HEIGHT
            )

            if (isBackButtonEnabled) {
                RoundedBackButton(onNavigateBackClicked = { onNavigateBackClicked() })
            }

            RoundedFavouriteButton(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        end = MaterialTheme.spacing.large,
                        top = MaterialTheme.spacing.large
                    ),
                isFavourite = isFavourite,
                onFavouriteClicked = { onFavouriteClicked() }
            )
        }

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(
                corner = CornerSize(spaceBetween)
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Box(modifier = modifier.padding(MaterialTheme.spacing.normal)) {
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

@PreviewScreenSizes
@PreviewLightDark
@Composable
private fun DetailsScaffoldPreview(
    @PreviewParameter(WindowSizeParameterProvider::class) windowSize: WindowSizeClass
) {
    FoodyTheme {
        DetailsScaffold(
            windowSize = windowSize,
            headerImageUrl = "",
            isFavourite = false,
            onFavouriteClicked = { },
            onHeaderImageClicked = {}
        ) {
            NormalText(text = "this is the content section")
        }
    }
}
