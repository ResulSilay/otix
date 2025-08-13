package com.otix.mobile.feature.fuel.presentation.screen.capacity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otix.core.compose.recomposeHighlighter
import com.otix.core.resources.alias.Str
import com.otix.core.ui.component.OtixButton
import com.otix.core.ui.component.OtixHorizontalSlider
import com.otix.core.ui.provider.LocalStrings

@Composable
internal fun FuelCapacitySheet(
    uiState: FuelCapacityContract.State,
    onSave: (fuelVolume: Int, batteryCapacity: Int) -> Unit,
) {
    val strings = LocalStrings.current

    var fuelVolumeSliderValue by remember { mutableIntStateOf(uiState.fuelVolume) }
    var batteryCapacitySliderValue by remember { mutableIntStateOf(uiState.batteryCapacity) }

    LaunchedEffect(uiState.fuelVolume) {
        fuelVolumeSliderValue = uiState.fuelVolume
    }

    LaunchedEffect(uiState.batteryCapacity) {
        batteryCapacitySliderValue = uiState.batteryCapacity
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .recomposeHighlighter(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OtixHorizontalSlider(
            value = fuelVolumeSliderValue.toFloat(),
            valueRange = 0F..200F,
            unit = strings[Str.fuel_capacity_unit_text],
            onValueChange = { value ->
                fuelVolumeSliderValue = value.toInt()
            }
        )

        Spacer(modifier = Modifier.height(height = 48.dp))

        OtixHorizontalSlider(
            value = batteryCapacitySliderValue.toFloat(),
            valueRange = 0F..200F,
            unit = strings[Str.fuel_capacity_battery_unit_text],
            onValueChange = { value ->
                batteryCapacitySliderValue = value.toInt()
            }
        )

        Spacer(modifier = Modifier.height(height = 48.dp))

        OtixButton(text = strings[Str.default_button_save_text]) {
            onSave(fuelVolumeSliderValue, batteryCapacitySliderValue)
        }
    }
}