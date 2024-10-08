package mende273.foody.common.ui.component.mealdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import mende273.foody.common.ui.component.NormalText
import mende273.foody.common.ui.component.SmallButton
import mende273.foody.common.ui.component.mealdetails.detailsscaffold.DetailsScaffold
import mende273.foody.common.ui.component.mealdetails.detailsscaffold.DetailsScaffoldSection
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.mediumTextStyle
import mende273.foody.common.ui.theme.spacing
import mende273.foody.core.domain.model.MealDetails

@Composable
fun MealDetailsComponent(
    modifier: Modifier = Modifier,
    mealDetails: MealDetails,
    windowSize: WindowSizeClass,
    onHeaderImageClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    isBackButtonEnabled: Boolean,
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit,
    onAreaClicked: (String) -> Unit,
    onVideoClicked: (String) -> Unit,
    onSourceClicked: (String) -> Unit,
    onIngredientClicked: (String) -> Unit,
    onNavigateBackClicked: () -> Unit = {}
) {
    DetailsScaffold(
        modifier = modifier.verticalScroll(rememberScrollState()),
        windowSize = windowSize,
        isBackButtonEnabled = isBackButtonEnabled,
        isFavourite = isFavourite,
        headerImageUrl = mealDetails.thumb,
        onNavigateBackClicked = { onNavigateBackClicked() },
        onHeaderImageClicked = { onHeaderImageClicked(it) },
        onFavouriteClicked = { onFavouriteClicked() },
        contents = {
            Column(modifier) {
                DetailsScaffoldSection(
                    title = mealDetails.name,
                    isHeaderTitle = true,
                    addBottomSpace = false
                )

                DetailsScaffoldSection(content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            MaterialTheme.spacing.small
                        )
                    ) {
                        if (mealDetails.category.isNotEmpty()) {
                            SmallButton(text = mealDetails.category, onClicked = {
                                onCategoryClicked(mealDetails.category)
                            })
                        }

                        if (mealDetails.area.isNotEmpty()) {
                            SmallButton(text = mealDetails.area, onClicked = {
                                onAreaClicked(mealDetails.area)
                            })
                        }
                    }
                })

                if (mealDetails.instructions.isNotEmpty()) {
                    DetailsScaffoldSection(title = "Instructions", content = {
                        NormalText(
                            text = mealDetails.instructions,
                            textStyle = mediumTextStyle()
                        )
                    })
                }

                if (mealDetails.ingredientsWithMeasures.isNotEmpty()) {
                    DetailsScaffoldSection(title = "Ingredients", content = {
                        IngredientsWithMeasuresGridComponent(
                            items = mealDetails.ingredientsWithMeasures,
                            onIngredientClicked = {
                                onIngredientClicked(it)
                            }
                        )
                    })
                }

                if (mealDetails.tags.isNotEmpty()) {
                    DetailsScaffoldSection(title = "Tags", content = {
                        NormalText(
                            text = mealDetails.tags,
                            textStyle = mediumTextStyle()
                        )
                    })
                }

                if (mealDetails.youtube.isNotEmpty() || mealDetails.source.isNotEmpty()) {
                    DetailsScaffoldSection(title = "More Info", content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                MaterialTheme.spacing.normal
                            )
                        ) {
                            if (mealDetails.youtube.isNotEmpty()) {
                                SmallButton(text = "Video", onClicked = {
                                    onVideoClicked(mealDetails.youtube)
                                })
                            }

                            if (mealDetails.source.isNotEmpty()) {
                                SmallButton(text = "Source", onClicked = {
                                    onSourceClicked(mealDetails.source)
                                })
                            }
                        }
                    })
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
            }
        }
    )
}

@PreviewScreenSizes
@PreviewLightDark
@Composable
private fun MealDetailsComponentPreview(
    @PreviewParameter(MealDetailsComponentParameterProvider::class)
    previewModel: MealDetailsComponentPreviewModel
) {
    FoodyTheme {
        MealDetailsComponent(
            mealDetails = previewModel.mealDetails,
            windowSize = previewModel.windowSizeClass,
            onHeaderImageClicked = {},
            onCategoryClicked = {},
            isBackButtonEnabled = false,
            isFavourite = previewModel.isFavourite,
            onFavouriteClicked = { },
            onAreaClicked = {},
            onVideoClicked = {},
            onSourceClicked = {},
            onIngredientClicked = {}
        )
    }
}
