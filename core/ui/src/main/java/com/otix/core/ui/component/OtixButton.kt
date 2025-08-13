package com.otix.core.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtixButton(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.primary,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    onClick: () -> Unit,
) {
    val interactionSource = remember(::MutableInteractionSource)
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96F else 1F,
        label = "ScaleAnimate"
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .height(height = 45.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        onClick = onClick,
        shape = RoundedCornerShape(size = 32.dp),
        interactionSource = interactionSource,
        colors = colors
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = textColor
        )
    }
}
