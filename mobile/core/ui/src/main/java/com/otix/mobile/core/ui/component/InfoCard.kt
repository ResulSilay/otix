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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.ui.component.OtixCard
import com.otix.core.ui.ext.conditional

@Composable
fun InfoCard(
    title: String,
    value: String,
    status: Boolean,
    isFillMaxSize: Boolean = true
) {
    OtixCard(
        isFillMaxSize = isFillMaxSize,
        isCardStatus = status
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .conditional(condition = !isFillMaxSize) {
                    padding(vertical = 32.dp)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = title,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = value,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}