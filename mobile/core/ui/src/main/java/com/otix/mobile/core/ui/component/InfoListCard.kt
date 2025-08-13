package com.otix.mobile.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.otix.core.ui.component.OtixHorizontalDivider

@Composable
fun InfoListCard(
    title: String,
    values: List<Any>,
    status: Boolean
) {
    OtixCard(isCardStatus = status) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                if (values.isNotEmpty()) {
                    values.forEach {
                        Text(
                            modifier = Modifier.padding(
                                vertical = 4.dp,
                                horizontal = 12.dp
                            ),
                            text = it.toString(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.labelMedium
                        )

                        if (values.size > 1) {
                            OtixHorizontalDivider(
                                modifier = Modifier.padding(vertical = 4.dp),
                            )
                        }
                    }
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "-",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}
