package com.otix.mobile.feature.trip.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.otix.core.resources.alias.Draw

@Composable
fun TripLocationCard(
    modifier: Modifier = Modifier,
    startAddressText: String,
    endAddressText: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(size = 16.dp),
                painter = painterResource(id = Draw.ic_circle),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5F),
                contentDescription = "icon"
            )

            Circles(
                modifier = Modifier.padding(vertical = 6.dp)
            )

            Icon(
                modifier = Modifier.size(size = 16.dp),
                painter = painterResource(id = Draw.ic_location),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5F),
                contentDescription = "icon"
            )
        }

        Column(
            modifier = Modifier
                .weight(weight = 1F)
                .padding(start = 16.dp)
        ) {
            TripLocationAddressItem(
                addressText = startAddressText
            )

            Spacer(
                modifier = Modifier.height(height = 16.dp)
            )

            TripLocationAddressItem(
                addressText = endAddressText
            )
        }
    }
}

@Composable
private fun TripLocationAddressItem(addressText: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                brush = SolidColor(value = MaterialTheme.colorScheme.outline.copy(alpha = 0.1F)),
                shape = RoundedCornerShape(size = 8.dp),
            )
            .background(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1F),
                shape = RoundedCornerShape(size = 8.dp),
            )
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = addressText,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5F)
        )
    }
}

@Composable
private fun Circles(
    modifier: Modifier = Modifier,
    count: Int = 3
) = Column(
    modifier = modifier.padding(all = 8.dp),
    verticalArrangement = Arrangement.spacedBy(space = 2.dp)
) {
    repeat(times = count) {
        Box(
            modifier = Modifier
                .size(size = 4.dp)
                .background(
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4F),
                    shape = CircleShape
                )
        )
    }
}
