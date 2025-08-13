package com.otix.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
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
fun OtixStatusBadge(
    modifier: Modifier = Modifier,
    status: Boolean,
    isIcon: Boolean = true,
    statusText: String = "",
    colors: List<Color> = listOf(
        Color.Gray.copy(alpha = 0.2F),
        Color.DarkGray.copy(alpha = 0.15f)
    )
) {
    val statusIcon = if (status) Icons.Rounded.Check else Icons.Rounded.Clear
    val statusTextColor = if (status) Color.Black else Color.White

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(topStart = 6.dp))
            .background(
                brush = Brush.linearGradient(colors = colors)
            )
    ) {
        if (isIcon) {
            Icon(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .size(size = 14.dp),
                imageVector = statusIcon,
                contentDescription = "icon",
                tint = Color.Gray.copy(alpha = 0.4f),
            )
        } else {
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = 4.dp,
                        vertical = 2.dp
                    )
                    .alpha(alpha = 0.3f),
                text = statusText,
                fontWeight = FontWeight.Normal,
                color = statusTextColor,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}