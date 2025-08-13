package com.otix.automotive.screen.home.template

import android.location.Location
import androidx.car.app.CarContext
import androidx.car.app.model.Header
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.core.graphics.drawable.IconCompat
import com.otix.automotive.screen.home.component.OtixRowItem
import com.otix.automotive.screen.home.tab.Tabs
import com.otix.core.resources.alias.Draw
import com.otix.core.resources.alias.Str
import com.otix.core.resources.alias.StrArray
import com.otix.core.resources.strings.Strings
import com.otix.shared.domain.model.PhoneInfo
import com.otix.shared.domain.model.VehicleInfo

internal fun CarContext.getLocationTemplate(
    strings: Strings,
    vehicleInfo: VehicleInfo,
    phoneInfo: PhoneInfo,
): PaneTemplate {
    val pane = Pane.Builder()
        .addRow(
            OtixRowItem(
                title = strings[Str.card_sensor_hardware_location_title],
                text = getLocationText(
                    strings = strings,
                    location = vehicleInfo.hardwareLocation.location.value
                ),
                iconCompat = IconCompat.createWithResource(this@CarContext, Draw.ic_vehicle)
            )
        )
        .addRow(
            OtixRowItem(
                title = strings[Str.card_sensor_gps_location_title],
                text = getLocationText(
                    strings = strings,
                    location = phoneInfo.location
                ),
                iconCompat = IconCompat.createWithResource(this@CarContext, Draw.ic_satellite)
            )
        )
        .build()

    return PaneTemplate.Builder(pane)
        .setHeader(
            Header.Builder()
                .setTitle(strings.getArray(id = StrArray.automotive_tabs)[Tabs.LOCATION.position])
                .build()
        )
        .build()
}

private fun getLocationText(strings: Strings, location: Location?): String =
    if (location != null) {
        buildString {
            append(strings[Str.sensor_location_latitude_text])
            append(": ")
            append(location.latitude)
            append(", ")
            append(strings[Str.sensor_location_longitude_text])
            append(": ")
            append(location.longitude)
        }
    } else {
        strings[Str.default_empty_state_text]
    }