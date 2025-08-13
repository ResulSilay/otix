package com.otix.mobile.feature.fuel.presentation.screen.fuel

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
import com.otix.core.extension.orZero
import com.otix.core.navigation.route.FuelCapacity
import com.otix.core.resources.alias.Draw
import com.otix.core.resources.alias.Str
import com.otix.core.ui.provider.LocalNavigator
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.core.ui.component.EnergyLevelCard
import com.otix.mobile.core.ui.component.FuelCapacityCard
import com.otix.mobile.core.ui.component.InfoHeader
import com.otix.mobile.core.ui.component.InfoListCard
import com.otix.mobile.core.ui.component.RangeRemainingCard
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.domain.type.EvConnectorType
import com.otix.shared.domain.type.FuelType

@Composable
fun FuelScreen(
    vehicleInfo: VehicleInfo,
    gridState: LazyGridState = rememberLazyGridState(),
    isDemoAccount: Boolean,
) {
    val navigator = LocalNavigator.current
    val strings = LocalStrings.current

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .recomposeHighlighter(),
        state = gridState,
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            FuelCapacityCard(
                iconRes = Draw.ic_fuel_station,
                title = strings[Str.fuel_info_card_title],
                value = strings[Str.fuel_info_card_text],
                editButtonText = strings[Str.fuel_info_edit_button_text],
                onClick = {
                    navigator.navigate(
                        route = FuelCapacity(
                            vehicleId = vehicleInfo.vehicleId,
                            isDemoAccount = isDemoAccount
                        )
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(height = 16.dp))
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(
                title = strings[Str.card_info_fuel_info_title],
                hasNoTopPadding = true
            )
        }

        item {
            InfoListCard(
                title = strings[Str.card_info_fuel_types_title],
                values = vehicleInfo.energyProfile.fuelTypes.value?.map {
                    FuelType.fromCode(code = it).description
                }.orEmpty(),
                status = vehicleInfo.energyProfile.fuelTypes.status
            )
        }

        item {
            InfoListCard(
                title = strings[Str.card_info_ev_connector_types_title],
                values = vehicleInfo.energyProfile.evConnectorTypes.value?.map {
                    EvConnectorType.fromCode(code = it).description
                }.orEmpty(),
                status = vehicleInfo.energyProfile.evConnectorTypes.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_fuel_percent_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            EnergyLevelCard(
                value = vehicleInfo.energyLevel.fuelPercent.value.orZero(),
                isEnergyLow = vehicleInfo.energyLevel.energyIsLow.value,
                energyLowText = strings[Str.energy_low_text],
                energyNotLowText = strings[Str.energy_not_low_text],
                energyUnit = vehicleInfo.energyLevel.fuelVolumeDisplayUnit.value,
                distanceUnit = vehicleInfo.energyLevel.distanceDisplayUnit.value,
                status = vehicleInfo.energyLevel.fuelPercent.status
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            InfoHeader(title = strings[Str.card_info_battery_percent_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            EnergyLevelCard(
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
            InfoHeader(title = strings[Str.card_info_range_remaining_meters_title])
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            RangeRemainingCard(
                rangeMeters = vehicleInfo.energyLevel.rangeRemainingMeters.value.orZero(),
                fuelPercentage = vehicleInfo.energyLevel.fuelPercent.value.orZero(),
                status = vehicleInfo.energyLevel.rangeRemainingMeters.status
            )
        }

        item {
            Spacer(modifier = Modifier.height(height = 96.dp))
        }
    }
}