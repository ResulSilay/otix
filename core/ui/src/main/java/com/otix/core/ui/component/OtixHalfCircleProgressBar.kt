package com.otix.core.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

@Composable
fun OtixHalfCircleProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 50F,
    maxProgress: Float = 100F,
    backgroundColor: Color = Color(0x20494949),
    gradientColors: List<Color> = listOf(Color.Red, Color.Yellow, Color.Green),
    strokeWidth: Float = 30F
) {
    val normalizedProgress = (progress / maxProgress).coerceIn(0F, 1F)
    val percentageText = "${(normalizedProgress * 100).toInt()}%"

    Box(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .aspectRatio(2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val radius = min(a = width, b = height) / 2
            val arcSize = Size(width = radius * 2, radius * 2)

            val topLeft = Offset(x = (width - arcSize.width) / 2, y = height - arcSize.height)

            drawArc(
                color = backgroundColor,
                startAngle = 180F,
                sweepAngle = 180F,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )

            val gradientBrush = Brush.linearGradient(
                colors = gradientColors,
                start = Offset(x = 0F, y = height),
                end = Offset(x = width, y = height)
            )

            drawArc(
                brush = gradientBrush,
                startAngle = 180F,
                sweepAngle = 180F * normalizedProgress,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }

        Text(
            text = percentageText,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
