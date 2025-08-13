package com.otix.shared.domain.type

enum class TollCardState(val value: Int) {
    UNKNOWN(0),
    VALID(1),
    INVALID(2),
    NOT_INSERTED(3);

    companion object {

        fun fromState(value: Int?): TollCardState {
            return TollCardState.entries.find { it.value == value } ?: UNKNOWN
        }
    }
}