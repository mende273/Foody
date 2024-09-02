package mende273.foody.common.ui.component.mealsgrid

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.R
import mende273.foody.common.ui.component.NetworkImage
import mende273.foody.common.ui.component.NormalText
import mende273.foody.common.ui.component.RoundedComponent
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.mediumTextStyle
import mende273.foody.core.domain.model.Meal

@Composable
fun MealItemComponent(
    modifier: Modifier = Modifier,
    meal: Meal,
    contentDescription: String
) {
    Column(modifier) {
        RoundedComponent {
            NetworkImage(
                url = meal.thumb,
                contentDescription = contentDescription,
                withCrossFade = true,
                contentScale = ContentScale.Inside,
                error = painterResource(R.drawable.image_placeholder)
            )
        }
        if (meal.name.isNotEmpty()) {
            NormalText(text = meal.name, textStyle = mediumTextStyle())
        }
    }
}

@PreviewLightDark
@Composable
private fun MealItemComponentPreview() {
    FoodyTheme {
        MealItemComponent(
            meal = Meal(id = 1234L, name = "Beef with cheese", thumb = ""),
            contentDescription = ""
        )
    }
}
