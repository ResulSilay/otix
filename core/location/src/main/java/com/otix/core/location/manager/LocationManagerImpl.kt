package com.otix.core.location.manager

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.Locale

class LocationManagerImpl(
    private val context: Context,
) : LocationManager {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private var locationCallback: LocationCallback? = null

    private val locationRequest: LocationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        15_000L
    ).apply {
        setMinUpdateIntervalMillis(15_000L)
        setWaitForAccurateLocation(true)
    }.build()

    override fun hasLocationPermissions(): Boolean {
        val fine = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarse = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val status = fine && coarse
        return status
    }

    override fun requestPermissions(
        activity: Activity,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
    ) {
        val permissions = REQUIRED_PERMISSIONS.toMutableList()
        permissionLauncher.launch(input = permissions.toTypedArray())
    }

    @SuppressLint("MissingPermission")
    override fun getLastKnownLocation(onLocationResult: (Location?) -> Unit) {
        if (!hasLocationPermissions()) {
            onLocationResult(null)
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                onLocationResult(location)
            }
            .addOnFailureListener {
                onLocationResult(null)
            }
    }

    @SuppressLint("MissingPermission")
    override fun startLocationUpdates(onLocationChanged: (Location) -> Unit) {
        if (!hasLocationPermissions()) return

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let(onLocationChanged)
            }
        }

        locationCallback?.let { callback ->
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                callback,
                Looper.getMainLooper()
            )
        }
    }

    override fun getAddressFromLocation(location: Location): MutableList<Address>? = runCatching {
        val geocoder = Geocoder(context, Locale.getDefault())
        @Suppress("DEPRECATION")
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
    }.getOrNull()

    override fun stopLocationUpdates() {
        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
        locationCallback = null
    }

    private companion object {

        val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}
