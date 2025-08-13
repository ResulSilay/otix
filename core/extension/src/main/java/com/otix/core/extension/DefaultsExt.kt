package com.otix.core.extension

val Boolean?.orFalse: Boolean
    get() = this ?: false

fun String?.isNoneText(): String = if (isNullOrBlank()) "-" else this

fun Boolean?.isNoneText(): String = this?.toString() ?: "-"

fun Int?.isNoneText(): String = if (this == null || this < 0) "-" else toString()

fun Float?.isNoneText(): String = if (this == null || this < 0) "-" else toString()

fun Double?.isNoneText(): String = this?.toString() ?: "-"

fun Double?.orZero() = this ?: 0.0

fun Int?.orZero() = this ?: 0

fun Float?.orZero() = this ?: 0F

fun Float?.isNullOrNegative(): Boolean = this?.let { it < 0F } ?: true

fun Long?.orZero() = this ?: 0L
