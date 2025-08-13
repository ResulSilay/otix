package com.otix.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.otix.core.ui.ext.conditional
import com.otix.core.ui.theme.Colors

@Composable
fun OtixCard(
    modifier: Modifier = Modifier,
    isFillMaxSize: Boolean = false,
    isCardStatus: Boolean = false,
    isBadgeVisible: Boolean = true,
    roundedCornerShapeSize: Dp = 8.dp,
    borderColor: Color = Color.White,
    backgroundColor: Color = Color.DarkGray,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = Colors.Card.getColors()
    val shape = RoundedCornerShape(size = roundedCornerShapeSize)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            )
            .conditional(condition = isFillMaxSize) {
                aspectRatio(ratio = 1f)
            }
            .shadow(
                elevation = 12.dp,
                shape = shape,
                ambientColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15F),
                spotColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15F)
            )
            .clickable(onClick = onClick::invoke),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            backgroundColor.copy(alpha = 0.08F),
                            backgroundColor.copy(alpha = 0.00F)
                        )
                    ),
                    shape = shape
                )
                .border(
                    width = 0.35.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            borderColor.copy(alpha = 0.16F),
                            borderColor.copy(alpha = 0.06F)
                        )
                    ),
                    shape = shape
                )
        ) {
            Box(
                modifier = Modifier
                    .conditional(condition = isFillMaxSize) {
                        fillMaxSize()
                    }
                    .conditional(condition = !isFillMaxSize) {
                        fillMaxWidth()
                    }
            ) {
                content()

                if (isBadgeVisible) {
                    OtixStatusBadge(
                        modifier = Modifier.align(alignment = Alignment.BottomEnd),
                        status = isCardStatus,
                        colors = colors.badge
                    )
                }
            }
        }
    }
}
