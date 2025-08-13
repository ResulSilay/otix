package com.otix.mobile.home.page

internal enum class PageType(val position: Int) {
    Dashboard(0),
    Fuel(1),
    Sensor(2),
    Map(3),
    Trips(4);

    companion object {

        fun parse(position: Int): PageType {
            return PageType.entries.firstOrNull { page -> page.position == position } ?: Dashboard
        }
    }
}