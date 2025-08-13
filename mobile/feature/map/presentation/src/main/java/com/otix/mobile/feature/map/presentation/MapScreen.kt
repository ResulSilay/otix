package com.otix.mobile.feature.map.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.otix.core.compose.recomposeHighlighter
import com.otix.core.extension.hasLocationPermission
import com.otix.core.extension.isNoneText
import com.otix.core.resources.alias.Draw
import com.otix.core.resources.alias.Str
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.feature.map.domain.tile.mapTileSources
import com.otix.mobile.feature.map.presentation.component.MapLocationItem
import com.otix.mobile.feature.map.presentation.component.MapView
import com.otix.shared.domain.model.PhoneInfo
import com.otix.shared.domain.model.VehicleInfo

@Composable
fun MapScreen(phoneInfo: PhoneInfo, vehicleInfo: VehicleInfo, isDemoAccount: Boolean) {
    val context = LocalContext.current
    val strings = LocalStrings.current
    val phoneLocation = phoneInfo.location
    val hardwareLocation = vehicleInfo.hardwareLocation.location.value
    val hasLocationPermission = context.hasLocationPermission()

    val isDarkTheme = isSystemInDarkTheme()
    val colorScheme = MaterialTheme.colorScheme

    var startAnimation by remember { mutableStateOf(true) }

    val mapTileSource = remember(isDarkTheme) {
        if (isDarkTheme) mapTileSources.last() else mapTileSources.first()
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1F else 0F,
        animationSpec = tween(durationMillis = 300),
        label = "mapAlphaAnimate"
    )

    val gradientBrush = remember(alphaAnim, colorScheme) {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0F to colorScheme.background.copy(alpha = alphaAnim),
                0.5F to colorScheme.background.copy(alpha = alphaAnim * 0.9F),
                0.7F to colorScheme.background.copy(alpha = alphaAnim * 0.6F),
                0.85F to colorScheme.background.copy(alpha = alphaAnim * 0.3F),
                1F to Color.Transparent
            ),
            startY = Float.POSITIVE_INFINITY,
            endY = 0F
        )
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (map, bottomBar) = createRefs()

        MapView(
            modifier = Modifier
                .constrainAs(ref = map) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxSize()
                .recomposeHighlighter(),
            tileSources = mapTileSource,
            carLocation = hardwareLocation,
            gpsLocation = phoneLocation,
            zoomLevel = if (hasLocationPermission) 18.0 else 15.0
        )

        if (hasLocationPermission || isDemoAccount) {
            Box(
                modifier = Modifier
                    .constrainAs(ref = bottomBar) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(height = 300.dp)
                    .background(brush = gradientBrush),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    MapLocationItem(
                        title = strings[Str.card_sensor_gps_location_title],
                        color = Color.Red,
                        iconResId = Draw.ic_satellite,
                        latitude = phoneLocation?.latitude.isNoneText(),
                        longitude = phoneLocation?.longitude.isNoneText(),
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        thickness = 0.4.dp,
                        color = colorScheme.onSurfaceVariant.copy(alpha = 0.1F)
                    )

                    MapLocationItem(
                        title = strings[Str.card_sensor_hardware_location_title],
                        color = colorScheme.onSurfaceVariant,
                        iconResId = Draw.ic_vehicle,
                        latitude = hardwareLocation?.latitude.isNoneText(),
                        longitude = hardwareLocation?.longitude.isNoneText(),
                    )
                }
            }
        }
    }
}