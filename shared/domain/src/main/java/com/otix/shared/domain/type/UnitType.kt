package com.otix.shared.domain.type

enum class UnitType(val value: Int, val abbreviation: String) {
    UNKNOWN(-1, ""),
    MILLIMETER(1, "mm"),
    METER(2, "m"),
    KILOMETER(3, "km"),
    MILE(4, "mi"),
    METERS_PER_SEC(101, "m/s"),
    KILOMETERS_PER_HOUR(102, "km/h"),
    MILES_PER_HOUR(103, "mph"),
    MILLILITER(201, "mL"),
    LITER(202, "L"),
    US_GALLON(203, "gal (US)"),
    IMPERIAL_GALLON(204, "gal (Imp)");

    companion object {

        fun fromCode(code: Int?): UnitType {
            return UnitType.entries.find { it.value == code } ?: UNKNOWN
        }
    }
}
