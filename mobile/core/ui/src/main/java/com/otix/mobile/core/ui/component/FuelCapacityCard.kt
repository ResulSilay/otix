package com.otix.mobile.core.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.otix.core.ui.component.OtixButton
import com.otix.core.ui.component.OtixCard
import com.otix.core.ui.component.OtixHorizontalDivider

@Composable
fun FuelCapacityCard(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    title: String,
    value: String,
    editButtonText: String,
    tintColor: Color = Color(color = 0xFF15832A),
    borderColor: Color = Color(color = 0xFF16FF41),
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onClick: () -> Unit,
) {
    OtixCard(
        modifier = modifier.fillMaxWidth(),
        borderColor = borderColor,
        backgroundColor = tintColor,
        isBadgeVisible = false,
        onClick = onClick
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(
                    modifier = Modifier
                        .size(size = 48.dp)
                        .padding(start = 16.dp),
                    painter = painterResource(id = iconRes),
                    tint = textColor,
                    contentDescription = "icon",
                )

                Column(
                    modifier = Modifier.padding(all = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        color = textColor,
                        style = MaterialTheme.typography.labelMedium
                    )

                    Spacer(modifier = Modifier.height(height = 8.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = value,
                        color = textColor,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            OtixHorizontalDivider()

            OtixButton(
                text = editButtonText,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                colors = ButtonDefaults.buttonColors(
                    containerColor = tintColor.copy(alpha = 0.1F),
                    contentColor = tintColor.copy(alpha = 0.1F),
                ),
                onClick = onClick::invoke
            )
        }
    }
}