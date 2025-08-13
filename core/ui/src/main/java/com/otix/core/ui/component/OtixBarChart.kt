package com.otix.core.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtixBarChart(
    modifier: Modifier = Modifier,
    values: List<Pair<Long, Float>>,
    minValue: Float = 0F,
    maxValue: Float = 100F,
    height: Dp = 150.dp,
    barColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    tooltipBackgroundColor: Color = Color.DarkGray,
    tooltipTextColor: Color = Color.White,
    tooltipTextSize: TextUnit = 12.sp,
    emptyStateText: String = "",
) {
    if (values.isEmpty()) {
        OtixEmptyState(
            modifier = modifier,
            text = emptyStateText
        )
        return
    }

    val scrollState = rememberScrollState()
    var touchedIndex by remember { mutableStateOf<Int?>(null) }
    val textMeasurer = rememberTextMeasurer()

    val sortedData = values.sortedBy { it.first }

    val valueRange = (maxValue - minValue).takeIf { it != 0F } ?: 1F

    val barSpacing = 8.dp
    val barWidth = 32.dp

    val barSpacingPx = with(LocalDensity.current) { barSpacing.toPx() }
    val barWidthPx = with(LocalDensity.current) { barWidth.toPx() }
    val fullBarWidthPx = barWidthPx + barSpacingPx
    val totalWidthPx = fullBarWidthPx * sortedData.size

    val chartHeightDp = height
    val chartHeightPx = with(LocalDensity.current) { chartHeightDp.toPx() }

    Box(
        modifier = modifier
            .height(height = chartHeightDp + 40.dp)
            .horizontalScroll(scrollState)
    ) {
        Canvas(
            modifier = Modifier
                .width(width = with(LocalDensity.current) { totalWidthPx.toDp() })
                .height(height = chartHeightDp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val index = (offset.x / fullBarWidthPx).toInt()
                        touchedIndex = if (index in sortedData.indices) index else null
                    }
                }
        ) {
            sortedData.forEachIndexed { index, (_, value) ->
                val barHeightRatio = (value - minValue) / valueRange
                val barHeight = barHeightRatio * chartHeightPx
                val left = index * fullBarWidthPx
                val top = chartHeightPx - barHeight

                drawRoundRect(
                    color = barColor,
                    topLeft = Offset(x = left, y = top),
                    size = Size(width = barWidthPx, barHeight),
                    cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx())
                )
            }

            touchedIndex?.let { index ->
                if (index in sortedData.indices) {
                    val (_, value) = sortedData[index]
                    val barCenterX = index * fullBarWidthPx + barWidthPx / 2
                    val text = "%.1f".format(value)

                    val textLayoutResult = textMeasurer.measure(
                        AnnotatedString(text),
                        style = TextStyle(color = tooltipTextColor, fontSize = tooltipTextSize)
                    )

                    val tooltipWidth = textLayoutResult.size.width.toFloat() + 24.dp.toPx()
                    val tooltipHeight = textLayoutResult.size.height.toFloat() + 16.dp.toPx()
                    val tooltipX = (barCenterX - tooltipWidth / 2).coerceIn(
                        minimumValue = 0F,
                        maximumValue = size.width - tooltipWidth
                    )
                    val tooltipY = 0F

                    drawRoundRect(
                        color = tooltipBackgroundColor,
                        topLeft = Offset(x = tooltipX, y = tooltipY),
                        size = Size(tooltipWidth, tooltipHeight),
                        cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx())
                    )

                    drawIntoCanvas {
                        drawText(
                            textLayoutResult,
                            topLeft = Offset(
                                x = tooltipX + (tooltipWidth - textLayoutResult.size.width) / 2,
                                y = tooltipY + (tooltipHeight - textLayoutResult.size.height) / 2
                            )
                        )
                    }
                }
            }
        }
    }
}