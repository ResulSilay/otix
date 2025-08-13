package com.otix.automotive.screen.home.template

import androidx.car.app.CarContext
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.Header
import androidx.car.app.model.ItemList
import androidx.core.graphics.drawable.IconCompat
import com.otix.automotive.R
import com.otix.automotive.screen.home.component.OtixGridItem
import com.otix.automotive.screen.home.tab.Tabs
import com.otix.core.extension.isNoneText
import com.otix.core.extension.toKmFormattedString
import com.otix.core.resources.alias.Str
import com.otix.core.resources.alias.StrArray
import com.otix.core.resources.strings.Strings
import com.otix.shared.domain.model.VehicleInfo

internal fun CarContext.getFuelTemplate(
    strings: Strings,
    vehicleInfo: VehicleInfo
): GridTemplate {
    val item = ItemList.Builder()
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_fuel_percent_title],
                text = vehicleInfo.energyLevel.fuelPercent.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_fuel)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_battery_percent_title],
                text = vehicleInfo.energyLevel.batteryPercent.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_fuel)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_range_remaining_meters_title],
                text = "${vehicleInfo.energyLevel.rangeRemainingMeters.value?.toKmFormattedString().isNoneText()} km",
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_battery_low)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_fuel_unit_title],
                text = vehicleInfo.energyLevel.fuelVolumeDisplayUnit.value?.abbreviation.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_fuel)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_distance_unit_title],
                text = vehicleInfo.energyLevel.distanceDisplayUnit.value?.abbreviation.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_battery_low)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_energy_low_title],
                text = vehicleInfo.energyLevel.energyIsLow.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_battery_low)
            )
        )
        .build()

    return GridTemplate.Builder()
        .setHeader(
            Header.Builder()
                .setTitle(strings.getArray(id = StrArray.automotive_tabs)[Tabs.FUEL.position])
                .build()
        )
        .setSingleList(item)
        .build()
}