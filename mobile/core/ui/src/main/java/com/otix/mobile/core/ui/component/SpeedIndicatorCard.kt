package com.otix.mobile.core.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.otix.core.extension.isNoneText
import com.otix.core.extension.toFormattedString
import com.otix.core.ui.component.OtixCard
import com.otix.shared.domain.type.VehicleState

@Composable
fun SpeedIndicatorCard(
    modifier: Modifier = Modifier,
    title: String,
    unitText: String,
    speed: Float,
    maxSpeed: Int = 300,
    vehicleState: VehicleState,
    status: Boolean
) {
    val speedKmPerHour = speed * 3.6F
    val fillRatio = (speedKmPerHour / maxSpeed).coerceIn(0F, 1F)
    val gradientColors = listOf(Color.Red, Color.Yellow, Color.Green)

    OtixCard(
        isCardStatus = status
    ) {
        Box(
            modifier = modifier
                .padding(
                    start = 16.dp,
                    top = 24.dp,
                    bottom = 24.dp
                )
                .fillMaxHeight()
                .width(width = 20.dp)
                .height(height = 155.dp)
                .clip(shape = RoundedCornerShape(size = 10.dp))
                .background(color = Color(color = 0x144F4F4F))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = fillRatio)
                    .align(alignment = Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(colors = gradientColors)
                    )
                    .clip(shape = RoundedCornerShape(size = 10.dp))
            )
        }

        Column(
            modifier = Modifier
                .padding(
                    start = 60.dp,
                    top = 36.dp
                )
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            Text(
                text = speedKmPerHour.toFormattedString().isNoneText(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(height = 1.dp))

            Text(
                text = unitText,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Text(
                text = vehicleState.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .padding(end = 24.dp)
                .size(size = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(color = Color.Gray.copy(alpha = 0.05f), radius = size.minDimension / 2)
                drawCircle(color = Color.Gray.copy(alpha = 0.08f), radius = size.minDimension / 3)
            }

            Icon(
                modifier = Modifier.size(size = 40.dp),
                imageVector = Icons.Default.Speed,
                tint = Color.Gray.copy(alpha = 0.3f),
                contentDescription = "icon"
            )
        }
    }
}
