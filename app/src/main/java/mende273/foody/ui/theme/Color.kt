package mende273.foody.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkThemeBackground = Color(color = 0xFF000000)
val LightThemeBackground = Color(color = 0xfff6f6f6)

val LightThemePrimary = Color(color = 0xFF000000)
val DarkThemePrimary = Color(color = 0xffffffff)

val AccentColor = Color(color = 0xfff1e900)
val MediumDarkGreyColor = Color(color = 0xFF717171)

val NavigationBarSelectedColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) LightThemePrimary else AccentColor
