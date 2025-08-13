package com.otix.automotive.screen.permission

internal fun getAutoPermissions(): List<String> = listOf(
    "com.google.android.gms.permission.CAR_FUEL",
    "com.google.android.gms.permission.CAR_SPEED",
    "com.google.android.gms.permission.CAR_MILEAGE",
    "android.permission.ACCESS_FINE_LOCATION"
)