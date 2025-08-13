package com.otix.mobile.core.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.extension.orZero
import com.otix.core.resources.alias.Str
import com.otix.core.ui.component.OtixCard
import com.otix.core.ui.provider.LocalStrings
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CompassCard(
    values: List<Float>,
    status: Boolean
) {
    OtixCard(
        isCardStatus = status
    ) {
        val azimuth: Float = values.getOrNull(index = 0)?.takeIf { !it.isNaN() && it.isFinite() }.orZero()
        val pitch: Float = values.getOrNull(index = 1)?.takeIf { !it.isNaN() && it.isFinite() }.orZero()
        val roll: Float = values.getOrNull(index = 2)?.takeIf { !it.isNaN() && it.isFinite() }.orZero()

        val direction: String = remember(azimuth) { getCompassDirection(degree = azimuth) }

        val drawColor = MaterialTheme.colorScheme.onSecondaryContainer

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(size = 300.dp)
                            .border(
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = CircleShape
                            )
                            .background(color = Color.Black, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        CompassCanvasContent()

                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .rotate(
                                    degrees = azimuth
                                        .takeIf(Float::isFinite)
                                        .orZero()
                                )
                        ) {
                            val center = size.center
                            val arrowHeight = 245F
                            val arrowWidth = 60F

                            val path = Path().apply {
                                moveTo(x = center.x, y = center.y - arrowHeight)
                                lineTo(
                                    x = center.x - arrowWidth / 2,
                                    y = center.y + arrowHeight / 3
                                )
                                lineTo(x = center.x, y = center.y + arrowHeight / 2)
                                lineTo(
                                    x = center.x + arrowWidth / 2,
                                    y = center.y + arrowHeight / 3
                                )
                                close()
                            }

                            drawPath(path = path, color = drawColor)
                        }
                    }
                }
            }

            CompassInfoCard(
                azimuth = azimuth,
                direction = direction,
                pitch = pitch,
                roll = roll
            )
        }
    }
}

@Composable
private fun CompassInfoCard(azimuth: Float, direction: String, pitch: Float, roll: Float) {
    val strings = LocalStrings.current

    Column(
        modifier = Modifier
            .padding(
                top = 32.dp,
                bottom = 24.dp
            )
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${strings[Str.sensor_azimuth_text]}: ${azimuth.toDisplayString()}° ($direction)",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(height = 8.dp))

        Text(
            text = "${strings[Str.sensor_pitch_text]}: ${pitch.toDisplayString()}°",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(height = 8.dp))

        Text(
            text = "${strings[Str.sensor_roll_text]}: ${roll.toDisplayString()}°",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun CompassCanvasContent() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = size.center
        val radius = size.minDimension / 2.1F
        val degreePadding = 16
        val directionPadding = 112

        for (degree in 0 until 360 step 6) {
            val angleInRadians = Math.toRadians((degree - 90).toDouble())
            val lineLength = if (degree % 30 == 0) 20F else 10F
            val startX = center.x + cos(x = angleInRadians) * (radius - lineLength)
            val startY = center.y + sin(x = angleInRadians) * (radius - lineLength)
            val endX = center.x + cos(x = angleInRadians) * radius
            val endY = center.y + sin(x = angleInRadians) * radius
            drawLine(
                color = Color.Gray,
                start = Offset(x = startX.toFloat(), y = startY.toFloat()),
                end = Offset(x = endX.toFloat(), y = endY.toFloat()),
                strokeWidth = if (degree % 30 == 0) 3F else 2F
            )
        }

        for (degree in 0 until 360 step 30) {
            val angleInRadians = Math.toRadians((degree - 90).toDouble())
            val x = center.x + cos(x = angleInRadians) * (radius - 35F - degreePadding)
            val y = center.y + sin(x = angleInRadians) * (radius - 35F - degreePadding)
            drawContext.canvas.nativeCanvas.drawText(
                degree.toString(),
                x.toFloat(),
                y.toFloat(),
                android.graphics.Paint().apply {
                    color = android.graphics.Color.LTGRAY
                    textSize = 24f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }

        val mainDirections = listOf("N", "E", "S", "W")
        val mainAngles = listOf(0, 90, 180, 270)

        for (i in mainDirections.indices) {
            val angleDegrees = mainAngles[i]
            val angleInRadians = Math.toRadians((angleDegrees - 90).toDouble())
            val x = center.x + cos(x = angleInRadians) * (radius - directionPadding)
            val y = center.y + sin(x = angleInRadians) * (radius - directionPadding)
            drawContext.canvas.nativeCanvas.drawText(
                mainDirections[i],
                x.toFloat(),
                y.toFloat(),
                android.graphics.Paint().apply {
                    color = android.graphics.Color.WHITE
                    textSize = 36f
                    textAlign = android.graphics.Paint.Align.CENTER
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                }
            )
        }
    }
}

private fun getCompassDirection(degree: Float): String {
    if (degree.isNaN()) return "N/A"
    val directions = listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
    val index = ((degree + 22.5) % 360 / 45).toInt()
    return directions[index]
}

private fun Float.toDisplayString(): String =
    takeIf { !isNaN() }?.let { "%.1f".format(it) } ?: "N/A"