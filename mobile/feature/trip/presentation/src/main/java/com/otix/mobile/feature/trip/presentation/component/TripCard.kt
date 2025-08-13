package com.otix.mobile.feature.trip.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.ui.component.OtixCard

@Composable
fun TripCard(
    dateTitle: String,
    distanceTitle: String,
    distanceValueText: String,
    fuelTitle: String,
    fuelValueText: String,
    startAddressText: String,
    endAddressText: String,
    onClick: () -> Unit
) {
    OtixCard(
        isBadgeVisible = false,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = dateTitle,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TripInfoColumn(
                    title = distanceTitle,
                    value = distanceValueText
                )

                TripInfoColumn(
                    title = fuelTitle,
                    value = fuelValueText
                )
            }

            Spacer(modifier = Modifier.height(height = 16.dp))

            TripLocationCard(
                startAddressText = startAddressText,
                endAddressText = endAddressText
            )
        }
    }
}

@Composable
private fun TripInfoColumn(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray.copy(alpha = 0.7F)
        )

        Spacer(modifier = Modifier.height(height = 8.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}