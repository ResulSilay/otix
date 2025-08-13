package com.otix.core.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.otix.core.resources.alias.Draw

@Composable
fun OtixEmptyState(
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp,
    isIconVisible: Boolean = true,
    @DrawableRes iconResId: Int = Draw.ic_storage,
    text: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isIconVisible) {
            Icon(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(size = iconSize),
                painter = painterResource(id = iconResId),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5F),
                contentDescription = "icon"
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            text = text,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5F),
            style = MaterialTheme.typography.labelMedium
        )
    }
}