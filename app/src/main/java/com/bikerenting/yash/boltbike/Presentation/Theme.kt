package com.bikerenting.yash.boltbike.Presentation

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun BoltBikeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkBoltBikeColorScheme
        else -> LightBoltBikeColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )


}

private val LightBoltBikeColorScheme = lightColorScheme(
    primary = PrimaryOrange,
    secondary = AccentNavy,         // App bars, headings
    tertiary = LightOrange,         // Selected background or contrast elements
    background = AppBackground,
    surface = CardBackground,
    onPrimary = TextOnButton,       // Text on PrimaryOrange
    onSecondary = TextPrimary,      // Text on navy
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = ErrorRed,
    onError = TextOnButton
)

private val DarkBoltBikeColorScheme = darkColorScheme(
    primary = PrimaryOrange,
    secondary = AccentNavy,
    tertiary = LightOrange,
    background = AlmostBlack,
    surface = MutedComponents,
    onPrimary = TextOnButton,
    onSecondary = AppBackground,
    onBackground = AppBackground,
    onSurface = AppBackground,
    error = ErrorRed,
    onError = AppBackground
)
