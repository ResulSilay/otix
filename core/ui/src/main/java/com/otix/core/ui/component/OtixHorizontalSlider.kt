package com.otix.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OtixHorizontalSlider(
    modifier: Modifier = Modifier,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    unit: String = "",
    steps: Int = 0,
    onValueChange: (Float) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .background(
                    shape = RoundedCornerShape(size = 8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1F)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 4.dp
                )
        ) {
            Text(
                text = "$value $unit",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge
            )
        }

        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 32.dp),
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                activeTrackColor = MaterialTheme.colorScheme.onSurfaceVariant,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface,
                disabledThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledActiveTrackColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledActiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledInactiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledInactiveTrackColor = MaterialTheme.colorScheme.onSurfaceVariant,
                activeTickColor = MaterialTheme.colorScheme.onSurfaceVariant,
                inactiveTickColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3F)
            )
        )
    }
}
