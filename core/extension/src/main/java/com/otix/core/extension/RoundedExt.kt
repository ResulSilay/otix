package com.otix.core.extension

import kotlin.math.abs

fun Float.toFormattedString(tolerance: Double = 0.00001): String =
    if (abs(x = this % 1.0) < tolerance) {
        this.toInt().toString()
    } else {
        "%.2f".format(this)
    }

fun Float.toKmFormattedString(tolerance: Double = 0.00001): String {
    val km = this / 1000F
    return if (abs(x = km % 1.0) < tolerance) {
        km.toInt().toString()
    } else {
        "%.2f".format(km)
    }
}
