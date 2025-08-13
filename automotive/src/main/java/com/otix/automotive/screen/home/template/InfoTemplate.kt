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
import com.otix.core.extension.orZero
import com.otix.core.extension.toFormattedString
import com.otix.core.resources.alias.Str
import com.otix.core.resources.alias.StrArray
import com.otix.core.resources.strings.Strings
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.domain.type.TollCardState

internal fun CarContext.getInfoTemplate(
    strings: Strings,
    vehicleInfo: VehicleInfo
): GridTemplate {
    val item = ItemList.Builder()
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_name_title],
                text = vehicleInfo.model.name.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_model)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_manufacturer_title],
                text = vehicleInfo.model.manufacturer.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_manufacturer)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_year_title],
                text = vehicleInfo.model.year.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_year)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_speed_header_title],
                text = (vehicleInfo.speed.displaySpeedMetersPerSecond.value.orZero() * 3.6F)
                    .toFormattedString()
                    .isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_speed)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_raw_speed_title],
                text = vehicleInfo.speed.rawSpeedMetersPerSecond.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_speed)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_speed_display_unit_title],
                text = vehicleInfo.speed.speedDisplayUnit.value?.abbreviation.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_speed)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_odometer_title],
                text = "${vehicleInfo.mileage.odometerMeters.value.isNoneText()} m",
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_odometer)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_odometer_meters_unit_title],
                text = vehicleInfo.mileage.distanceDisplayUnit.value?.abbreviation.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_odometer)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_ev_change_open_title],
                text = vehicleInfo.evStatus.evChargePortOpen.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_ev_charger)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_ev_connector_types_title],
                text = vehicleInfo.energyProfile.evConnectorTypes.value?.firstOrNull().isNoneText(),
                iconCompat = IconCompat.createWithResource(
                    this@CarContext,
                    R.drawable.ic_vehicle_ev_connector_type
                )
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_ev_change_connected_title],
                text = vehicleInfo.evStatus.evChargePortConnected.value.isNoneText(),
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_ev_connect)
            )
        )
        .addItem(
            OtixGridItem(
                title = strings[Str.card_info_toll_card_state_title],
                text = TollCardState.fromState(value = vehicleInfo.tollCard.cardState.value).name,
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_toll)
            )
        )
        .build()

    return GridTemplate.Builder()
        .setHeader(
            Header.Builder()
                .setTitle(strings.getArray(id = StrArray.automotive_tabs)[Tabs.INFO.position])
                .build()
        )
        .setSingleList(item)
        .build()
}