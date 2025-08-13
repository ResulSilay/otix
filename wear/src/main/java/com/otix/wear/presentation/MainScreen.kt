package com.otix.wear.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.otix.core.extension.isNoneText
import com.otix.core.extension.toFormattedString
import com.otix.core.extension.toKmFormattedString
import com.otix.core.resources.alias.Str
import com.otix.core.resources.strings.Strings
import com.otix.shared.domain.type.TollCardState
import com.otix.wear.R

@Composable
internal fun MainScreen(
    strings: Strings,
    uiState: MainContract.State,
) {
    val listState = rememberScalingLazyListState()
    val carInfo = uiState.vehicleInfo

    val infoCards = listOf(
        Triple(
            strings[Str.card_info_name_title],
            carInfo.model.name.value.isNoneText(),
            R.drawable.ic_car_model
        ),
        Triple(
            strings[Str.card_info_manufacturer_title],
            carInfo.model.manufacturer.value.isNoneText(),
            R.drawable.ic_car_manufacturer
        ),
        Triple(
            strings[Str.card_info_year_title],
            carInfo.model.year.value.isNoneText(),
            R.drawable.ic_car_year
        ),
        Triple(
            strings[Str.card_info_speed_header_title],
            carInfo.speed.displaySpeedMetersPerSecond.value?.toFormattedString().isNoneText(),
            R.drawable.ic_car_speed
        ),
        Triple(
            strings[Str.card_info_raw_speed_title],
            carInfo.speed.rawSpeedMetersPerSecond.value.isNoneText(),
            R.drawable.ic_car_speed
        ),
        Triple(
            strings[Str.card_info_speed_display_unit_title],
            carInfo.speed.speedDisplayUnit.value?.abbreviation.isNoneText(),
            R.drawable.ic_car_speed
        ),
        Triple(
            strings[Str.card_info_odometer_title],
            "${carInfo.mileage.odometerMeters.value.isNoneText()} m",
            R.drawable.ic_car_odometer
        ),
        Triple(
            strings[Str.card_info_odometer_meters_unit_title],
            carInfo.mileage.distanceDisplayUnit.value?.abbreviation.isNoneText(),
            R.drawable.ic_car_odometer
        ),
        Triple(
            strings[Str.card_info_ev_change_open_title],
            carInfo.evStatus.evChargePortOpen.value.isNoneText(),
            R.drawable.ic_car_ev_charger
        ),
        Triple(
            strings[Str.card_info_ev_connector_types_title],
            carInfo.energyProfile.evConnectorTypes.value?.firstOrNull().isNoneText(),
            R.drawable.ic_car_ev_connector_type
        ),
        Triple(
            strings[Str.card_info_ev_change_connected_title],
            carInfo.evStatus.evChargePortConnected.value.isNoneText(),
            R.drawable.ic_car_ev_connect
        ),
        Triple(
            strings[Str.card_info_toll_card_state_title],
            TollCardState.fromState(value = carInfo.tollCard.cardState.value).name,
            R.drawable.ic_car_toll
        )
    )

    val fuelCards = listOf(
        Triple(
            strings[Str.card_info_fuel_percent_title],
            carInfo.energyLevel.fuelPercent.value.isNoneText(),
            R.drawable.ic_car_fuel
        ),
        Triple(
            strings[Str.card_info_battery_percent_title],
            carInfo.energyLevel.batteryPercent.value.isNoneText(),
            R.drawable.ic_car_fuel
        ),
        Triple(
            strings[Str.card_info_range_remaining_meters_title],
            "${carInfo.energyLevel.rangeRemainingMeters.value?.toKmFormattedString()} km",
            R.drawable.ic_car_battery_low
        ),
        Triple(
            strings[Str.card_info_fuel_unit_title],
            carInfo.energyLevel.fuelVolumeDisplayUnit.value?.abbreviation.isNoneText(),
            R.drawable.ic_car_fuel
        ),
        Triple(
            strings[Str.card_info_distance_unit_title],
            carInfo.energyLevel.distanceDisplayUnit.value?.abbreviation.isNoneText(),
            R.drawable.ic_car_battery_low
        ),
        Triple(
            strings[Str.card_info_energy_low_title],
            carInfo.energyLevel.energyIsLow.value.isNoneText(),
            R.drawable.ic_car_battery_low
        )
    )

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 16.dp),
            state = listState
        ) {
            item {
                ListHeader(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = strings[Str.wear_info_header_text])
                }
            }

            items(infoCards) { (title, text, iconRes) ->
                InfoChip(
                    title = title,
                    text = text,
                    iconRes = iconRes
                )
            }

            item {
                ListHeader(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = strings[Str.wear_fuel_header_text])
                }
            }

            items(fuelCards) { (title, text, iconRes) ->
                InfoChip(
                    title = title,
                    text = text,
                    iconRes = iconRes
                )
            }
        }
    }
}

@Composable
private fun InfoChip(title: String, text: String, @DrawableRes iconRes: Int) {
    Chip(
        modifier = Modifier.fillMaxWidth(),
        onClick = { },
        label = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSurface
            )
        },
        secondaryLabel = {
            Text(
                text = text,
                color = MaterialTheme.colors.onSurfaceVariant
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = iconRes),
                tint = MaterialTheme.colors.onSurface,
                contentDescription = "icon"
            )
        },
        colors = ChipDefaults.chipColors(
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1F),
            contentColor = MaterialTheme.colors.onSurfaceVariant
        )
    )
}