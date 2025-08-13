package com.otix.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {

    object Card {

        data class Style(
            val border: List<Color>,
            val background: List<Color>,
            val badge: List<Color>,
            val ambientColor: Color,
            val spotColor: Color,
        )

        private val DarkDefault = Style(
            border = listOf(
                Color(0xFFFFFFFF).copy(alpha = 0.08f),
                Color(0xFFFFFFFF).copy(alpha = 0.03f)
            ),
            background = listOf(
                Color(0xFF1A1A1A),
                Color(0xFF222222),
                Color(0xFF2B2B2B)
            ),
            badge = listOf(
                Color(0x4A1F1F1F),
                Color(0x5C2A2A2A),
            ),
            ambientColor = Color.Gray.copy(alpha = 0.25f),
            spotColor = Color.Gray.copy(alpha = 0.25f)
        )

        private val LightDefault = Style(
            border = listOf(
                Color.LightGray.copy(alpha = 0.3f),
                Color.Gray.copy(alpha = 0.2f)
            ),
            background = listOf(
                Color(0xFFF5F5F5),
                Color(0xFFEFEFEF),
                Color(0xFFE0E0E0)
            ),
            badge = listOf(
                Color.LightGray.copy(alpha = 0.2f),
                Color.Gray.copy(alpha = 0.1f)
            ),
            ambientColor = Color.Gray.copy(alpha = 0.15f),
            spotColor = Color.Gray.copy(alpha = 0.2f)
        )

        @Composable
        fun getColors(darkTheme: Boolean = isSystemInDarkTheme()): Style = when {
            darkTheme -> DarkDefault
            else -> LightDefault
        }
    }
}