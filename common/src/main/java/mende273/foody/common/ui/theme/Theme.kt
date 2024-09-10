package mende273.foody.common.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = DarkThemePrimary,
    secondary = AccentColor,
    background = DarkThemeBackground,
    onBackground = DarkThemePrimary,
    surface = LightThemePrimary
)

private val lightColorScheme = lightColorScheme(
    primary = LightThemePrimary,
    secondary = AccentColor,
    background = LightThemeBackground,
    onBackground = LightThemePrimary,
    surface = DarkThemePrimary
)

@Composable
fun FoodyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            val windowsInsetsController = WindowCompat.getInsetsController(window, view)
            windowsInsetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
