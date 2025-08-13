package com.otix.mobile.feature.map.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.otix.core.extension.isNoneText

@Composable
internal fun MapLocationItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconResId: Int,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    latitude: String,
    longitude: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(size = 20.dp)
                .clip(shape = CircleShape),
            painter = painterResource(id = iconResId),
            tint = color.copy(alpha = 0.7F),
            contentDescription = ""
        )

        Text(
            modifier = Modifier
                .weight(weight = 1F)
                .padding(start = 16.dp),
            text = title,
            color = color.copy(alpha = 0.7F),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium
        )

        Text(
            modifier = Modifier.weight(weight = 1F),
            text = buildString {
                append(latitude.isNoneText())
                append(" , ")
                append(longitude.isNoneText())
            },
            color = color.copy(alpha = 0.7F),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
    }
}