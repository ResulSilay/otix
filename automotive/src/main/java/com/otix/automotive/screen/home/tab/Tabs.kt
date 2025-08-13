package com.otix.automotive.screen.home.tab

internal enum class Tabs(val id: String, val position: Int) {
    INFO(id = "tab_info", position = 0),
    FUEL(id = "tab_fuel", position = 1),
    SENSOR(id = "tab_sensor", position = 2),
    LOCATION(id = "tab_location", position = 3);

    companion object {

        fun parse(id: String): Tabs = Tabs.entries.firstOrNull { tab -> tab.id == id } ?: INFO
    }
}