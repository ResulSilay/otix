package com.otix.mobile.feature.trip.presentation.screen.map.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.otix.core.resources.alias.Color
import com.otix.core.resources.alias.Draw
import com.otix.mobile.feature.map.domain.tile.mapTileSources
import com.otix.shared.domain.model.GeoLocation
import kotlinx.collections.immutable.PersistentList
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@Composable
fun TripMapView(
    modifier: Modifier = Modifier,
    routePoints: PersistentList<GeoLocation>,
    onMarkerDetail: (routeIndex: Int) -> Unit
) {
    val tileSource = if (isSystemInDarkTheme()) {
        mapTileSources.last()
    } else {
        mapTileSources.first()
    }

    val routeCenterLocation = routePoints.getOrNull(index = routePoints.size.div(other = 2))

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            Configuration.getInstance().load(
                context,
                PreferenceManager.getDefaultSharedPreferences(context)
            )

            val mapView = MapView(context).apply {
                setMultiTouchControls(true)
                setTileSource(tileSource)
            }

            mapView
        },
        update = { mapView ->
            if (routePoints.isNotEmpty()) {
                val geoPoints = routePoints.map { GeoPoint(it.latitude, it.longitude) }

                val polyline = Polyline().apply {
                    setPoints(geoPoints)
                    outlinePaint.color = ContextCompat.getColor(mapView.context, Color.map_route_color)
                    outlinePaint.strokeWidth = 10F
                }

                mapView.overlays.add(polyline)

                geoPoints.forEachIndexed { index, geoPoint ->
                    val marker = Marker(mapView).apply {
                        icon = ContextCompat.getDrawable(mapView.context, Draw.ic_map_point)
                        title = "Point ${index + 1}"
                        position = geoPoint
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                        setOnMarkerClickListener { _, _ ->
                            onMarkerDetail(index)
                            true
                        }
                    }

                    mapView.overlays.add(marker)
                }
            }

            routeCenterLocation?.run {
                mapView.controller.setZoom(15.0)
                mapView.controller.setCenter(GeoPoint(latitude, longitude))
            }

            mapView.invalidate()
        }
    )
}