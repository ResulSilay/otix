package com.otix.shared.domain.type

enum class FuelType(val code: Int, val description: String) {
    UNKNOWN(0, "Unknown fuel type"),
    UNLEADED(1, "Unleaded gasoline"),
    LEADED(2, "Leaded gasoline"),
    DIESEL_1(3, "#1 Grade Diesel"),
    DIESEL_2(4, "#2 Grade Diesel"),
    BIODIESEL(5, "Biodiesel"),
    E85(6, "85% ethanol/gasoline blend"),
    LPG(7, "Liquified petroleum gas"),
    CNG(8, "Compressed natural gas"),
    LNG(9, "Liquified natural gas"),
    ELECTRIC(10, "Electric"),
    HYDROGEN(11, "Hydrogen fuel cell"),
    OTHER(12, "Other fuel type");

    companion object {

        fun fromCode(code: Int): FuelType {
            return entries.find { it.code == code } ?: UNKNOWN
        }
    }
}
