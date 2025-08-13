package com.otix.mobile.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.extension.toKmFormattedString
import com.otix.core.ui.component.OtixCard
import com.otix.core.ui.component.OtixHorizontalProgressBar
import com.otix.core.ui.ext.conditional

@Composable
fun RangeRemainingCard(
    title: String = "",
    rangeMeters: Float,
    fuelPercentage: Float,
    status: Boolean,
    isFillMaxSize: Boolean = false
) {
    val estimatedRangeFullTankKm = (rangeMeters / (fuelPercentage / 100))

    OtixCard(
        isFillMaxSize = isFillMaxSize,
        isCardStatus = status
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .conditional(condition = !isFillMaxSize) {
                    padding(vertical = 32.dp)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (title.isNotBlank()) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(height = 16.dp))
            }

            OtixHorizontalProgressBar(
                progress = rangeMeters,
                maxProgress = estimatedRangeFullTankKm
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 12.dp),
                text = "${rangeMeters.toKmFormattedString()} km",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}