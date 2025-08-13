package com.otix.core.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otix.core.extension.toDateFormat
import kotlin.math.abs

@Composable
fun OtixLineChart(
    modifier: Modifier = Modifier,
    values: List<Pair<Long, Float>>,
    minValue: Float = 0F,
    maxValue: Float = 100F,
    height: Dp = 150.dp,
    lineColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    fillStartColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    fillEndColor: Color = Color.Transparent,
    emptyStateText: String,
) {
    if (values.isEmpty()) {
        OtixEmptyState(
            modifier = modifier,
            text = emptyStateText
        )
        return
    }

    val scrollState = rememberScrollState()
    val sortedData = remember(values) { values.sortedBy { it.first } }

    val minTimestamp = sortedData.first().first.toFloat()
    val maxTimestamp = sortedData.last().first.toFloat()
    val timeRange = maxTimestamp - minTimestamp

    val valueRange = (maxValue - minValue).takeIf { it != 0F } ?: 1F

    var touchedPoint by remember { mutableStateOf<Offset?>(null) }
    var canvasSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    val pointSpacingPx = 48.dp
    val totalWidthPx = with(LocalDensity.current) { pointSpacingPx.toPx() * (sortedData.size - 1) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        Canvas(
            modifier = Modifier
                .width(width = with(LocalDensity.current) { totalWidthPx.toDp() })
                .height(height = height)
                .pointerInput(Unit) {
                    detectTapGestures { offset -> touchedPoint = offset }
                }
        ) {
            canvasSize = size

            val dataPoints = sortedData.map { (timestamp, value) ->
                val x = ((timestamp - minTimestamp) / timeRange) * size.width
                val y = size.height - ((value - minValue) / valueRange) * size.height
                Offset(x, y)
            }

            val fillPath = Path().apply {
                moveTo(x = dataPoints.first().x, y = size.height)
                lineTo(x = dataPoints.first().x, y = dataPoints.first().y)
                for (i in 1 until dataPoints.size) {
                    val prev = dataPoints[i - 1]
                    val curr = dataPoints[i]
                    val mid = Offset(x = (prev.x + curr.x) / 2, y = (prev.y + curr.y) / 2)
                    quadraticTo(x1 = prev.x, y1 = prev.y, x2 = mid.x, y2 = mid.y)
                }
                lineTo(x = dataPoints.last().x, y = size.height)
                close()
            }

            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(fillStartColor, fillEndColor),
                    startY = 0F,
                    endY = size.height
                )
            )

            val strokePath = Path().apply {
                moveTo(x = dataPoints.first().x, y = dataPoints.first().y)
                for (i in 1 until dataPoints.size) {
                    val prev = dataPoints[i - 1]
                    val curr = dataPoints[i]
                    val mid = Offset(x = (prev.x + curr.x) / 2, y = (prev.y + curr.y) / 2)
                    quadraticTo(x1 = prev.x, y1 = prev.y, x2 = mid.x, y2 = mid.y)
                }
            }

            drawPath(
                path = strokePath,
                color = lineColor,
                style = Stroke(width = 3f, cap = StrokeCap.Round)
            )

            touchedPoint?.let { offset ->
                val nearest = dataPoints.minByOrNull { abs(x = it.x - offset.x) }
                val index = dataPoints.indexOf(nearest)
                if (nearest != null && index >= 0) {
                    drawCircle(
                        color = Color.White,
                        center = nearest,
                        radius = 8f
                    )
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(x = nearest.x, y = 0F),
                        end = Offset(x = nearest.x, y = size.height),
                        strokeWidth = 1f
                    )
                }
            }
        }

        touchedPoint?.let { offset ->
            val dataPoints = sortedData.map { (timestamp, value) ->
                val x = ((timestamp - minTimestamp) / timeRange) * canvasSize.width
                val y = canvasSize.height - ((value - minValue) / valueRange) * canvasSize.height
                Offset(x, y)
            }

            val nearest = dataPoints.minByOrNull { abs(x = it.x - offset.x) }
            val index = dataPoints.indexOf(nearest)
            if (index >= 0) {
                val (timestamp, value) = sortedData[index]
                val tooltipText =
                    "${timestamp.toDateFormat(pattern = "HH:mm")} • ${"%.2f".format(value)}"

                Box(
                    modifier = Modifier
                        .offset { IntOffset(x = offset.x.toInt(), y = 10) }
                        .background(
                            Color.Black.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(tooltipText, color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}
