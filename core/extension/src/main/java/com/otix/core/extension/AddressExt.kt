package com.otix.core.extension

import android.location.Address

fun Address?.getAddress(): String =
    "${this?.featureName.orEmpty()}, ${this?.subLocality.orEmpty()}, ${this?.subAdminArea.orEmpty()}, ${this?.adminArea.orEmpty()}"