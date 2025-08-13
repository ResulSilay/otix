package com.otix.core.location.manager

import android.app.Activity
import android.location.Address
import android.location.Location
import androidx.activity.result.ActivityResultLauncher

interface LocationManager {

    fun hasLocationPermissions(): Boolean

    fun requestPermissions(activity: Activity, permissionLauncher: ActivityResultLauncher<Array<String>>)

    fun getLastKnownLocation(onLocationResult: (Location?) -> Unit)

    fun startLocationUpdates(onLocationChanged: (Location) -> Unit)

    fun getAddressFromLocation(location: Location): MutableList<Address>?

    fun stopLocationUpdates()
}
