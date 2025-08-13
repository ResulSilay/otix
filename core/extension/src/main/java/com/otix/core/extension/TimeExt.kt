package com.otix.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getTime(): Long = Date().time

fun Long.toDateFormat(pattern: String = ""): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date)
}