package com.otix.shared.domain.type

enum class EvConnectorType(val code: Int, val description: String) {
    UNKNOWN(0, "Unknown connector type"),
    J1772(1, "Connector type SAE J1772"),
    MENNEKES(2, "IEC 62196 Type 2 connector"),
    CHADEMO(3, "CHAdeMo fast charger connector"),
    COMBO_1(4, "Combined Charging System Combo 1"),
    COMBO_2(5, "Combined Charging System Combo 2"),
    TESLA_ROADSTER(6, "Connector of Tesla Roadster"),
    TESLA_HPWC(7, "High Power Wall Charger of Tesla"),
    TESLA_SUPERCHARGER(8, "Supercharger of Tesla"),
    GBT(9, "GBT_AC Fast Charging Standard"),
    GBT_DC(10, "GBT_DC Fast Charging Standard"),
    SCAME(11, "IEC_TYPE_3_AC connector"),
    OTHER(101, "Other");

    companion object {

        fun fromCode(code: Int): EvConnectorType {
            return entries.find { it.code == code } ?: UNKNOWN
        }
    }
}
