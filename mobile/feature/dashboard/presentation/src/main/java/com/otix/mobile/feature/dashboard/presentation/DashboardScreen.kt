package com.otix.mobile.feature.dashboard.presentation

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
import com.otix.core.extension.orZero
import com.otix.core.resources.alias.Str
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.core.ui.component.EnergyLevelCard
import com.otix.mobile.core.ui.component.InfoCard
import com.otix.mobile.core.ui.component.InfoHeader
import com.otix.mobile.core.ui.component.InfoListCard
import com.otix.mobile.core.ui.component.RangeRemainingCard
import com.otix.mobile.core.ui.component.SpeedIndicatorCard
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.domain.type.EvConnectorType
import com.otix.shared.domain.type.FuelType
import com.otix.shared.domain.type.TollCardState

@Composable
fun DashboardScreen(vehicleInfo: VehicleInfo, gridState: LazyGridState = rememberLazyGridState()) {
    val strings = LocalStrings.current

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .recomposeHighlighter(),
        state = gridState,
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(all = 8.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(
                title = strings[Str.card_info_vehicle_header_title],
                hasNoTopPadding = true
            )
        }

        item {
            InfoCard(
                title = strings[Str.card_info_vehicle_id_title],
                value = vehicleInfo.vehicleId.isNoneText(),
                status = true,
                isFillMaxSize = false
            )
        }

        item {
            InfoCard(
                title = strings[Str.card_info_manufacturer_title],
                value = vehicleInfo.model.manufacturer.value.isNoneText(),
                status = vehicleInfo.model.manufacturer.status,
                isFillMaxSize = false
            )
        }

        item {
            InfoCard(
                title = strings[Str.card_info_name_title],
                value = vehicleInfo.model.name.value.isNoneText(),
                status = vehicleInfo.model.name.status,
                isFillMaxSize = false
            )
        }

        item {
            InfoCard(
                title = strings[Str.card_info_year_title],
                value = vehicleInfo.model.year.value.isNoneText(),
                status = vehicleInfo.model.year.status,
                isFillMaxSize = false
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_speed_header_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            SpeedIndicatorCard(
                title = strings[Str.card_info_display_speed_title],
                unitText = "km/h",
                speed = vehicleInfo.speed.displaySpeedMetersPerSecond.value.orZero(),
                vehicleState = vehicleInfo.state,
                status = vehicleInfo.speed.displaySpeedMetersPerSecond.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_energy_profile_header_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoListCard(
                title = strings[Str.card_info_fuel_types_title],
                values = vehicleInfo.energyProfile.fuelTypes.value?.map {
                    FuelType.fromCode(code = it).description
                }.orEmpty(),
                status = vehicleInfo.energyProfile.fuelTypes.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoListCard(
                title = strings[Str.card_info_ev_connector_types_title],
                values = vehicleInfo.energyProfile.evConnectorTypes.value?.map {
                    EvConnectorType.fromCode(code = it).description
                }.orEmpty(),
                status = vehicleInfo.energyProfile.evConnectorTypes.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_energy_level_header_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            EnergyLevelCard(
                title = strings[Str.card_info_fuel_percent_title],
                value = vehicleInfo.energyLevel.fuelPercent.value.orZero(),
                energyLowText = strings[Str.energy_low_text],
                energyNotLowText = strings[Str.energy_not_low_text],
                isEnergyLow = vehicleInfo.energyLevel.energyIsLow.value,
                energyUnit = vehicleInfo.energyLevel.fuelVolumeDisplayUnit.value,
                distanceUnit = vehicleInfo.energyLevel.distanceDisplayUnit.value,
                status = vehicleInfo.energyLevel.fuelPercent.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            EnergyLevelCard(
                title = strings[Str.card_info_battery_percent_title],
                value = vehicleInfo.energyLevel.batteryPercent.value.orZero(),
                energyLowText = strings[Str.energy_low_text],
                energyNotLowText = strings[Str.energy_not_low_text],
                isEnergyLow = vehicleInfo.energyLevel.energyIsLow.value,
                energyUnit = vehicleInfo.energyLevel.fuelVolumeDisplayUnit.value,
                distanceUnit = vehicleInfo.energyLevel.distanceDisplayUnit.value,
                status = vehicleInfo.energyLevel.batteryPercent.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            RangeRemainingCard(
                title = strings[Str.card_info_range_remaining_meters_title],
                rangeMeters = vehicleInfo.energyLevel.rangeRemainingMeters.value.orZero(),
                fuelPercentage = vehicleInfo.energyLevel.fuelPercent.value.orZero(),
                status = vehicleInfo.energyLevel.rangeRemainingMeters.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_mileage_header_title])
        }

        item {
            InfoCard(
                title = strings[Str.card_info_distance_display_unit_title],
                value = vehicleInfo.mileage.distanceDisplayUnit.value?.abbreviation.isNoneText(),
                status = vehicleInfo.mileage.distanceDisplayUnit.status
            )
        }

        item {
            InfoCard(
                title = strings[Str.card_info_odometer_title],
                value = "${vehicleInfo.mileage.odometerMeters.value.isNoneText()} m",
                status = vehicleInfo.mileage.odometerMeters.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_ev_status_header_title])
        }

        item {
            InfoCard(
                title = strings[Str.card_info_charge_port_open_title],
                value = vehicleInfo.evStatus.evChargePortOpen.value.isNoneText(),
                status = vehicleInfo.evStatus.evChargePortOpen.status
            )
        }

        item {
            InfoCard(
                title = strings[Str.card_info_charge_port_connected_title],
                value = vehicleInfo.evStatus.evChargePortConnected.value.isNoneText(),
                status = vehicleInfo.evStatus.evChargePortConnected.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_toll_card_header_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoCard(
                title = strings[Str.card_info_card_state_title],
                value = TollCardState.fromState(value = vehicleInfo.tollCard.cardState.value).name,
                status = vehicleInfo.tollCard.cardState.status,
                isFillMaxSize = false
            )
        }

        item {
            Spacer(modifier = Modifier.height(height = 96.dp))
        }
    }
}