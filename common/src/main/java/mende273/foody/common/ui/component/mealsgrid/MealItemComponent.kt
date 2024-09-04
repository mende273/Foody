package mende273.foody.common.ui.component.mealsgrid

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import mende273.foody.common.ui.component.NormalText
import mende273.foody.common.ui.component.RoundedComponent
import mende273.foody.common.ui.theme.FoodyTheme
import mende273.foody.common.ui.theme.mediumTextStyle
import mende273.foody.core.domain.model.Meal
import mende273.foody.image.NetworkImage
import mende273.foody.image.NetworkImageContentScale

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
                contentScale = NetworkImageContentScale.INSIDE
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
