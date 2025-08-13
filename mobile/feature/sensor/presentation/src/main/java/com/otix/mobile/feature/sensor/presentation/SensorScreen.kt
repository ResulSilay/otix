package com.otix.mobile.feature.sensor.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otix.core.compose.recomposeHighlighter
import com.otix.core.extension.isNoneText
import com.otix.core.resources.alias.Str
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.core.ui.component.CompassCard
import com.otix.mobile.core.ui.component.InfoHeader
import com.otix.mobile.core.ui.component.InfoHorizontalCard
import com.otix.shared.domain.model.VehicleInfo

@Composable
fun SensorScreen(vehicleInfo: VehicleInfo, gridState: LazyGridState = rememberLazyGridState()) {
    val strings = LocalStrings.current

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .recomposeHighlighter(),
        state = gridState,
        columns = GridCells.Fixed(count = 1),
        contentPadding = PaddingValues(all = 8.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(
                title = strings[Str.card_sensor_accelerometer_title],
                hasNoTopPadding = true
            )
        }

        item {
            InfoHorizontalCard(
                titles = listOf(
                    strings[Str.sensor_x_axis_text],
                    strings[Str.sensor_y_axis_text],
                    strings[Str.sensor_z_axis_text],
                ),
                values = vehicleInfo.accelerometer.forces.value.orEmpty(),
                status = vehicleInfo.accelerometer.forces.status,
                emptyStateText = strings[Str.default_empty_state_text]
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_sensor_gyroscope_title])
        }

        item {
            InfoHorizontalCard(
                titles = listOf(
                    strings[Str.sensor_x_axis_text],
                    strings[Str.sensor_y_axis_text],
                    strings[Str.sensor_z_axis_text],
                ),
                values = vehicleInfo.gyroscope.rotations.value.orEmpty(),
                status = vehicleInfo.gyroscope.rotations.status,
                emptyStateText = strings[Str.default_empty_state_text]
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_sensor_compass_title])
        }

        item {
            CompassCard(
                values = vehicleInfo.compass.orientations.value.orEmpty(),
                status = vehicleInfo.compass.orientations.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_sensor_hardware_location_title])
        }

        item {
            InfoHorizontalCard(
                titles = listOf(
                    strings[Str.sensor_location_latitude_text],
                    strings[Str.sensor_location_longitude_text]
                ),
                values = listOf(
                    vehicleInfo.hardwareLocation.location.value?.latitude.isNoneText(),
                    vehicleInfo.hardwareLocation.location.value?.longitude.isNoneText(),
                ),
                status = vehicleInfo.compass.orientations.status,
                emptyStateText = strings[Str.default_empty_state_text]
            )
        }

        item {
            Spacer(modifier = Modifier.height(height = 96.dp))
        }
    }
}