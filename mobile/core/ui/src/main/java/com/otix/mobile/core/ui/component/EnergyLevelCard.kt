package com.otix.mobile.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.ui.component.OtixBadge
import com.otix.core.ui.component.OtixCard
import com.otix.core.ui.component.OtixHalfCircleProgressBar
import com.otix.core.ui.ext.conditional
import com.otix.shared.domain.type.UnitType

@Composable
fun EnergyLevelCard(
    title: String = "",
    value: Float,
    energyUnit: UnitType?,
    distanceUnit: UnitType?,
    status: Boolean,
    energyLowText: String = "",
    energyNotLowText: String = "",
    isEnergyLow: Boolean? = null,
    isFillMaxSize: Boolean = false
) {
    OtixCard(
        isFillMaxSize = isFillMaxSize,
        isCardStatus = status
    ) {
        Column(
            modifier = Modifier
                .conditional(condition = !isFillMaxSize) {
                    padding(
                        top = 32.dp,
                        bottom = 24.dp
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (title.isNotBlank()) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(height = 56.dp))
            } else {
                Spacer(modifier = Modifier.height(height = 24.dp))
            }

            OtixHalfCircleProgressBar(
                modifier = Modifier
                    .width(width = 300.dp)
                    .height(height = 75.dp),
                progress = value,
                maxProgress = 100F
            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                when (isEnergyLow) {
                    true -> OtixBadge(text = energyLowText)
                    false -> OtixBadge(text = energyNotLowText)
                    else -> Unit
                }

                if (energyUnit?.abbreviation?.isNotBlank() == true) {
                    Spacer(modifier = Modifier.width(width = 8.dp))

                    OtixBadge(text = energyUnit.abbreviation)
                }

                if (distanceUnit?.abbreviation?.isNotBlank() == true) {
                    Spacer(modifier = Modifier.width(width = 8.dp))

                    OtixBadge(text = distanceUnit.abbreviation)
                }
            }
        }
    }
}