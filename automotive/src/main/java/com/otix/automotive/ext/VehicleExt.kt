package com.otix.automotive.ext

import androidx.car.app.hardware.common.CarValue

fun Int.isSuccess() = this == CarValue.STATUS_SUCCESS