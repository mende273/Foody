package mende273.foody.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun largeTextStyle(): TextStyle {
    return normalTextStyle().copy(fontSize = 30.sp)
}

@Composable
fun mediumTextStyle(): TextStyle {
    return normalTextStyle().copy(fontSize = 20.sp)
}

@Composable
fun blackNormalTextStyle(): TextStyle {
    return normalTextStyle().copy(
        fontSize = 16.sp,
        color = LightThemePrimary
    )
}

@Composable
fun normalTextStyle(): TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.onBackground,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontSize = 16.sp,
        textAlign = TextAlign.Start
    )
}
