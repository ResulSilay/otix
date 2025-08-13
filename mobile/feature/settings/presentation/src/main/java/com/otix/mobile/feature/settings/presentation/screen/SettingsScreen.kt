package com.otix.mobile.feature.settings.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.otix.core.extension.isNoneText
import com.otix.core.resources.alias.Str
import com.otix.core.ui.component.OtixHorizontalDivider
import com.otix.core.ui.provider.LocalStrings
import com.otix.shared.domain.model.Vehicle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    uiState: SettingsContract.State,
    settingsController: SettingsController,
    selectedVehicleId: String,
    isDemoAccount: Boolean,
    onVehicleSelect: (vehicleId: String) -> Unit,
    onVehicleRemove: (vehicleId: String) -> Unit
) {
    val strings = LocalStrings.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(true) }

    LaunchedEffect(settingsController.isVisible) {
        if (settingsController.isVisible) {
            settingsController.open(sheetState = sheetState)
        }
    }

    if (settingsController.isVisible) {
        ModalBottomSheet(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 24.dp
                )
                .padding(horizontal = 16.dp),
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            shape = RoundedCornerShape(size = 8.dp),
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.1F
                    )
                )
            },
            onDismissRequest = {
                scope.launch {
                    settingsController.close(sheetState)
                }
            },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsVehicleExpandedCard(
                    text = strings[Str.settings_item_vehicle_title],
                    vehicles = uiState.vehicles,
                    selectedVehicleId = selectedVehicleId,
                    isExpanded = isExpanded,
                    isDemoAccount = isDemoAccount,
                    demoAccountText = strings[Str.vehicle_demo_account_button_text],
                    onExpandToggle = { isExpanded = !isExpanded },
                    onVehicleSelect = { vehicleId ->
                        onVehicleSelect(vehicleId)
                        scope.launch {
                            settingsController.close(sheetState)
                        }
                    },
                    onVehicleRemove = onVehicleRemove
                )
            }
        }
    }
}

@Composable
private fun SettingsVehicleHeader(
    modifier: Modifier = Modifier,
    text: String,
    isExpanded: Boolean = false,
    isDemoAccount: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(weight = 1F),
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (!isDemoAccount) {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(size = 16.dp)
                    .rotate(degrees = if (isExpanded) 0F else 180F),
                imageVector = Icons.Default.KeyboardArrowDown,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = "icon"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SettingsVehicleItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(size = 16.dp),
            imageVector = if (isSelected) {
                Icons.Filled.CheckCircle
            } else {
                Icons.Outlined.Circle
            },
            tint = if (isSelected) {
                Color.Green
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            contentDescription = "icon"
        )

        Text(
            modifier = Modifier.weight(weight = 1F),
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SettingsVehicleExpandedCard(
    text: String,
    vehicles: List<Vehicle>,
    selectedVehicleId: String,
    isExpanded: Boolean,
    isDemoAccount: Boolean,
    demoAccountText: String,
    onExpandToggle: () -> Unit,
    onVehicleSelect: (vehicleId: String) -> Unit,
    onVehicleRemove: (vehicleId: String) -> Unit
) {
    SettingsVehicleHeader(
        text = text,
        isExpanded = isExpanded,
        isDemoAccount = isDemoAccount,
        onClick = onExpandToggle
    )

    when {
        isExpanded -> {
            if (isDemoAccount) {
                SettingsVehicleItem(
                    isSelected = true,
                    text = demoAccountText
                )
            }
        }

        !isDemoAccount -> {
            vehicles.forEachIndexed { index, vehicle ->
                SettingsVehicleItem(
                    isSelected = vehicle.id == selectedVehicleId,
                    text = "${vehicle.id.isNoneText()} - ${vehicle.info.model.name.value.isNoneText()}",
                    onClick = {
                        onVehicleSelect(vehicle.id)
                    },
                    onLongClick = {
                        onVehicleRemove(vehicle.id)
                    }
                )

                if (index < vehicles.lastIndex) {
                    OtixHorizontalDivider()
                }
            }
        }

        else -> Unit
    }
}
