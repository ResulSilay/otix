package com.otix.automotive.screen.home.template

import androidx.car.app.CarContext
import androidx.car.app.model.Header
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.core.graphics.drawable.IconCompat
import com.otix.automotive.R
import com.otix.automotive.screen.home.component.OtixRowItem
import com.otix.automotive.screen.home.tab.Tabs
import com.otix.core.resources.alias.Str
import com.otix.core.resources.alias.StrArray
import com.otix.core.resources.strings.Strings
import com.otix.shared.domain.model.VehicleInfo

internal fun CarContext.getSensorTemplate(
    strings: Strings,
    vehicleInfo: VehicleInfo,
): PaneTemplate {
    val pane = Pane.Builder()
        .addRow(
            OtixRowItem(
                title = strings[Str.card_sensor_accelerometer_title],
                text = getSensorDataText(
                    labels = listOf(
                        strings[Str.sensor_x_axis_text],
                        strings[Str.sensor_y_axis_text],
                        strings[Str.sensor_z_axis_text],
                    ),
                    values = vehicleInfo.accelerometer.forces.value
                ).ifBlank {
                    strings[Str.default_empty_state_text]
                },
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_sensor_accelerometer)
            )
        )
        .addRow(
            OtixRowItem(
                title = strings[Str.card_sensor_gyroscope_title],
                text = getSensorDataText(
                    labels = listOf(
                        strings[Str.sensor_x_axis_text],
                        strings[Str.sensor_y_axis_text],
                        strings[Str.sensor_z_axis_text],
                    ),
                    values = vehicleInfo.gyroscope.rotations.value
                ).ifBlank {
                    strings[Str.default_empty_state_text]
                },
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_sensor_gyroscope)
            )
        )
        .addRow(
            OtixRowItem(
                title = strings[Str.card_sensor_compass_title],
                text = getSensorDataText(
                    labels = listOf(
                        strings[Str.sensor_bearing_text],
                        strings[Str.sensor_pitch_text],
                        strings[Str.sensor_roll_text],
                    ),
                    values = vehicleInfo.gyroscope.rotations.value
                ).ifBlank {
                    strings[Str.default_empty_state_text]
                },
                iconCompat = IconCompat.createWithResource(this@CarContext, R.drawable.ic_vehicle_sensor_compass)
            )
        )
        .build()

    return PaneTemplate.Builder(pane)
        .setHeader(
            Header.Builder()
                .setTitle(strings.getArray(id = StrArray.automotive_tabs)[Tabs.FUEL.position])
                .build()
        )
        .build()
}

private fun getSensorDataText(
    labels: List<String>,
    values: List<Float>?,
): String = if (values?.isNotEmpty() == true && values.size >= labels.size) {
    buildString {
        for (i in labels.indices) {
            append(labels[i])
            append(": ")
            append(values[i])
            if (i < labels.size - 1) append(", ")
        }
    }
} else {
    ""
}
