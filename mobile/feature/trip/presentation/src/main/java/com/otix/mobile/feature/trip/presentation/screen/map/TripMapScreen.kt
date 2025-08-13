package com.otix.mobile.feature.trip.presentation.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.otix.core.compose.recomposeHighlighter
import com.otix.mobile.feature.trip.presentation.screen.map.component.TripMapView
import com.otix.mobile.feature.trip.presentation.screen.map.info.TripMapInfoBar

@Composable
internal fun TripMapScreen(
    uiState: TripMapContract.State,
) {
    var selectedTripIndex by remember { mutableIntStateOf(0) }
    var showInfoBar by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.recomposeHighlighter(),
        bottomBar = {
            if (showInfoBar && uiState.tripInfo != null) {
                TripMapInfoBar(
                    index = selectedTripIndex,
                    tripInfo = uiState.tripInfo,
                    onDismissRequest = {
                        showInfoBar = false
                    }
                )
            }
        }
    ) { paddings ->
        TripMapView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddings),
            routePoints = uiState.routePoints,
            onMarkerDetail = { index ->
                selectedTripIndex = index
                showInfoBar = true
            }
        )
    }
}