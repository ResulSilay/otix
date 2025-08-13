package com.otix.mobile.home.tab

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.otix.core.resources.alias.Draw
import com.otix.core.resources.alias.Str

internal fun getTabs(): List<TabInfo> = listOf(
    TabInfo(
        titleRes = Str.mobile_tab_dashboard_text,
        iconRes = Draw.ic_circle
    ),
    TabInfo(
        titleRes = Str.mobile_tab_fuel_text,
        iconRes = Draw.ic_menu_fuel
    ),
    TabInfo(
        titleRes = Str.mobile_tab_sensor_text,
        iconRes = Draw.ic_menu_sensor
    ),
    TabInfo(
        titleRes = Str.mobile_tab_map_text,
        iconRes = Draw.ic_menu_map
    ),
    TabInfo(
        titleRes = Str.mobile_tab_trip_text,
        iconRes = Draw.ic_menu_trip
    )
)

internal data class TabInfo(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
)