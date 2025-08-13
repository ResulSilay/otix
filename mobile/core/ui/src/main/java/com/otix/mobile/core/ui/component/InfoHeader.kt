package com.otix.mobile.core.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InfoHeader(
    modifier: Modifier = Modifier,
    title: String,
    hasNoTopPadding: Boolean = false
) {
    Text(
        modifier = modifier
            .padding(
                top = if (hasNoTopPadding) 0.dp else 16.dp,
                bottom = 6.dp
            )
            .padding(horizontal = 12.dp),
        text = title,
        color = Color.Gray,
        fontWeight = FontWeight(weight = 300),
        style = MaterialTheme.typography.titleSmall
    )
}