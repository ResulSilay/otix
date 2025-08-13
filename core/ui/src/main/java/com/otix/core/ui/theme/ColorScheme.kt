package com.otix.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

internal val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    onPrimary = Black,
    onSecondary = Black,
    onBackground = Black,
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF383838),
    onSurfaceVariant = Color(0xFF000000),
    surfaceContainer = Color(0xFFFFFFFF),
    onSecondaryContainer = Color(0xFFFFFFFF)
)

internal val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF000000),
    secondary = Color(0xFF000000),
    background = Color(0xFF000000),
    onPrimary = Black,
    onSecondary = Black,
    onBackground = White,
    surface = Color(0xFF0C0C0C),
    onSurface = Color(0xFFBEBEBE),
    onSurfaceVariant = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFF1A1A1A),
    onSecondaryContainer = Color(0xFFFFFFFF)
)