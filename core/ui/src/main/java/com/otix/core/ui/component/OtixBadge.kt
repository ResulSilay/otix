package com.otix.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OtixBadge(
    modifier: Modifier = Modifier,
    text: String,
    colors: List<Color> = listOf(
        Color.White.copy(alpha = 0.1f),
        Color.White.copy(alpha = 0.1f)
    )
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 4.dp))
            .background(
                brush = Brush.linearGradient(colors = colors)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 6.dp,
                    vertical = 2.dp
                )
                .alpha(alpha = 0.75F),
            text = text,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall
        )
    }
}