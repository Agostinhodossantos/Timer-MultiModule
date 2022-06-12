package com.hubstaff.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BlackLight,
    primaryVariant = BlackLight,
    secondary = Teal200,
    secondaryVariant = TimerActiveDark,
    background = BlackLight,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val DarkTimerColorPalette = HubstaffColors(
    textPrimaryColor = Color.White,
    textSecondaryColor = TextSecondaryDark,
    searchBoxColor = GraySearchBoxDark,
    textVariantColor = Color.Black,
    isDark = true
)

@Composable
fun HubstaffTheme(content: @Composable () -> Unit) {
    val sysUiController = LocalSysUiController.current
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = DarkColorPalette.primary
        )
    }
    ProvideHubstaffColors(DarkTimerColorPalette) {
        MaterialTheme(
            colors = DarkColorPalette,
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }
}

object HubstaffTheme {
    val colors: HubstaffColors
        @Composable
        get() = LocalHubstaffColors.current
}

@Composable
fun ProvideHubstaffColors(
    colors: HubstaffColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalHubstaffColors provides colorPalette, content = content)
}

@Stable
class HubstaffColors(
    textPrimaryColor: Color,
    textSecondaryColor: Color,
    searchBoxColor: Color,
    textVariantColor: Color,
    isDark: Boolean
) {
    var textPrimaryColor by mutableStateOf(textPrimaryColor)
        private set
    var textSecondaryColor by mutableStateOf(textSecondaryColor)
        private set
    var searchBoxColor by mutableStateOf(searchBoxColor)
        private set
    var textVariantColor by mutableStateOf(textVariantColor)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: HubstaffColors) {
        textPrimaryColor = other.textPrimaryColor
        textSecondaryColor = other.textSecondaryColor
        searchBoxColor = other.searchBoxColor
        textVariantColor = other.textVariantColor
        isDark = other.isDark
    }
}

private val LocalHubstaffColors = staticCompositionLocalOf<HubstaffColors> {
    error("No ColorPalette provided")
}
