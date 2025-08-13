package com.otix.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OtixHorizontalProgressBar(
    progress: Float,
    maxProgress: Float = 100F
) {
    val fillRatio = (progress / maxProgress).coerceIn(0F, 1F)
    val gradientColors = listOf(Color.Red, Color.Yellow, Color.Green)

    Box(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(height = 20.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = Color(color = 0x20494949))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = fillRatio)
                .align(alignment = Alignment.CenterStart)
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors)
                )
                .clip(shape = RoundedCornerShape(size = 10.dp))
                .height(height = 20.dp)
        )
    }
}