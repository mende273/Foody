package mende273.foody.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import mende273.foody.R
import mende273.foody.domain.model.Meal
import mende273.foody.ui.preview.annotations.ThemePreviews
import mende273.foody.ui.theme.FoodyTheme
import mende273.foody.ui.theme.mediumTextStyle

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

@ThemePreviews
@Composable
private fun MealItemComponentPreview() {
    FoodyTheme {
        MealItemComponent(
            meal = Meal(id = 1234L, name = "Beef with cheese", thumb = ""),
            contentDescription = ""
        )
    }
}
