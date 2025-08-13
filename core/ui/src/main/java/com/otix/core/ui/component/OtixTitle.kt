package com.otix.core.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun OtixTitle(
    modifier: Modifier = Modifier,
    title: String,
    fontSize: TextUnit = 21.sp,
    fontWeight: FontWeight = FontWeight.ExtraBold,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    letterSpacing: Float = 15f
) {
    var isVisible by remember { mutableStateOf(false) }
    val spacing = remember { Animatable(0F) }

    val alphaAnim by animateFloatAsState(
        label = title,
        targetValue = if (isVisible) 1F else 0F,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(Unit) {
        isVisible = true
        spacing.animateTo(
            targetValue = letterSpacing,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        )
    }

    Text(
        modifier = modifier.graphicsLayer {
            alpha = alphaAnim
        },
        text = title,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        letterSpacing = spacing.value.sp,
    )
}
