package com.hubstaff.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color



private val LightColorPalette = lightColors(
    primary = HsBlue,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = HsDarkGrey,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

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
fun HubstaffMainTheme(content: @Composable () -> Unit) {
    val sysUiController = LocalSysUiController.current
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = DarkColorPalette.primary
        )
    }
    ProvideJettimerColors(DarkTimerColorPalette) {
        MaterialTheme(
            colors = DarkColorPalette,
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }
}
@Composable
fun HubstaffAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object HubstaffTheme {
    val colors: HubstaffColors
        @Composable
        get() = LocalJettimerColors.current
}

@Composable
fun ProvideJettimerColors(
    colors: HubstaffColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalJettimerColors provides colorPalette, content = content)
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

private val LocalJettimerColors = staticCompositionLocalOf<HubstaffColors> {
    error("No JetsnackColorPalette provided")
}
