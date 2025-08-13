package com.otix.mobile.feature.map.presentation.component

import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.otix.core.resources.alias.Draw
import com.otix.mobile.feature.map.domain.tile.mapTileSources
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
internal fun MapView(
    modifier: Modifier = Modifier,
    tileSources: XYTileSource = mapTileSources.last(),
    carLocation: Location?,
    gpsLocation: Location?,
    zoomLevel: Double = 10.0,
) {
    val context = LocalContext.current

    val carMarkerRef = remember { arrayOfNulls<Marker>(size = 1) }
    val gpsMarkerRef = remember { arrayOfNulls<Marker>(size = 1) }

    var lastTileSource by remember { mutableStateOf<XYTileSource?>(null) }
    var lastCarLocation by remember { mutableStateOf<Location?>(null) }
    var lastGpsLocation by remember { mutableStateOf<Location?>(null) }
    var lastZoomLevel by remember { mutableDoubleStateOf(zoomLevel) }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            Configuration.getInstance().load(
                context,
                PreferenceManager.getDefaultSharedPreferences(context)
            )

            MapView(context).apply {
                setMultiTouchControls(true)
                setTileSource(tileSources)
                controller.setZoom(zoomLevel)

                carLocation?.let {
                    val marker = Marker(this).apply {
                        position = GeoPoint(it.latitude, it.longitude)
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = ContextCompat.getDrawable(context, Draw.ic_car_hardware_location_marker)
                        title = "Vehicle"
                    }
                    carMarkerRef[0] = marker
                    overlays.add(marker)
                    controller.setCenter(marker.position)
                }

                gpsLocation?.let {
                    val marker = Marker(this).apply {
                        position = GeoPoint(it.latitude, it.longitude)
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = ContextCompat.getDrawable(context, Draw.ic_gps_location_marker)
                        title = "GPS"
                    }
                    gpsMarkerRef[0] = marker
                    overlays.add(marker)
                    controller.setCenter(marker.position)
                }

                lastTileSource = tileSources
                lastCarLocation = carLocation
                lastGpsLocation = gpsLocation
                lastZoomLevel = zoomLevel
            }
        },
        update = { mapView ->
            var needsInvalidate = false

            if (lastTileSource != tileSources) {
                mapView.setTileSource(tileSources)
                lastTileSource = tileSources
                needsInvalidate = true
            }

            carLocation?.let { loc ->
                val geoPoint = GeoPoint(loc.latitude, loc.longitude)
                val marker = carMarkerRef[0]

                if (marker != null) {
                    if (marker.position != geoPoint) {
                        marker.position = geoPoint
                        if (lastCarLocation != loc) {
                            mapView.controller.setCenter(geoPoint)
                            lastCarLocation = loc
                            needsInvalidate = true
                        }
                    }
                } else {
                    val newMarker = Marker(mapView).apply {
                        position = geoPoint
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = ContextCompat.getDrawable(context, Draw.ic_car_hardware_location_marker)
                        title = "Vehicle"
                    }
                    carMarkerRef[0] = newMarker
                    mapView.overlays.add(newMarker)
                    mapView.controller.setCenter(geoPoint)
                    lastCarLocation = loc
                    needsInvalidate = true
                }
            }

            gpsLocation?.let { loc ->
                val geoPoint = GeoPoint(loc.latitude, loc.longitude)
                val marker = gpsMarkerRef[0]

                if (marker != null) {
                    if (marker.position != geoPoint) {
                        marker.position = geoPoint
                        if (lastGpsLocation != loc) {
                            mapView.controller.setCenter(geoPoint)
                            lastGpsLocation = loc
                            needsInvalidate = true
                        }
                    }
                } else {
                    val newMarker = Marker(mapView).apply {
                        position = geoPoint
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = ContextCompat.getDrawable(context, Draw.ic_gps_location_marker)
                        title = "GPS"
                    }
                    gpsMarkerRef[0] = newMarker
                    mapView.overlays.add(newMarker)
                    mapView.controller.setCenter(geoPoint)
                    lastGpsLocation = loc
                    needsInvalidate = true
                }
            }

            if (lastZoomLevel != zoomLevel) {
                mapView.setZoomLevel(zoomLevel)
                lastZoomLevel = zoomLevel
                needsInvalidate = true
            }

            if (needsInvalidate) {
                mapView.invalidate()
            }
        }
    )
}
