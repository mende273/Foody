package mende273.foody.ui.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Theme")
@Preview(
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000
)
annotation class ThemePreviews
