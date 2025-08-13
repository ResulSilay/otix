package com.otix.mobile.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otix.core.resources.alias.Str
import com.otix.core.resources.strings.Strings
import com.otix.mobile.feature.dashboard.presentation.DashboardScreen
import com.otix.mobile.feature.fuel.presentation.screen.fuel.FuelScreen
import com.otix.mobile.feature.map.presentation.MapScreen
import com.otix.mobile.feature.sensor.presentation.SensorScreen
import com.otix.mobile.feature.settings.presentation.screen.SettingsController
import com.otix.mobile.feature.settings.presentation.screen.SettingsRoot
import com.otix.mobile.feature.trip.presentation.screen.trip.TripRoot
import com.otix.mobile.home.component.HomeNavBar
import com.otix.mobile.home.component.HomeTopBar
import com.otix.mobile.home.page.PageType
import com.otix.mobile.home.page.PageType.Dashboard
import com.otix.mobile.home.page.PageType.Fuel
import com.otix.mobile.home.page.PageType.Map
import com.otix.mobile.home.page.PageType.Sensor
import com.otix.mobile.home.page.PageType.Trips
import com.otix.shared.presentation.shared.SharedContract
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun HomeContent(
    modifier: Modifier = Modifier,
    strings: Strings,
    scope: CoroutineScope,
    pagerState: PagerState,
    uiState: SharedContract.State,
    dashboardGridState: LazyGridState,
    fuelGridState: LazyGridState,
    sensorGridState: LazyGridState,
    tripLazyListState: LazyListState,
    selectedTabIndex: State<Int>,
    settingsController: SettingsController,
    isNavBarVisible: Boolean,
    isShowMenuFab: Boolean,
    onVehicleSelect: (vehicleId: String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(title = strings[Str.app_title])
        },
        bottomBar = {
            if (isNavBarVisible) {
                HomeNavBar(
                    strings = strings,
                    scope = scope,
                    pagerState = pagerState,
                    selectedTabIndex = selectedTabIndex
                )
            }
        },
        floatingActionButton = {
            if (isShowMenuFab) {
                ExtendedFloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.background,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
                    shape = FloatingActionButtonDefaults.extendedFabShape,
                    icon = { },
                    text = {
                        Text(
                            text = strings[Str.mobile_home_menu_text],
                            color = MaterialTheme.colorScheme.onSurface,
                            letterSpacing = 5.sp
                        )
                    },
                    onClick = {
                        settingsController.isVisible = true
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddings ->
        Column(
            modifier = Modifier.padding(paddingValues = paddings)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(weight = 1f)
            ) { page ->
                when (PageType.parse(position = page)) {
                    Dashboard -> DashboardScreen(
                        vehicleInfo = uiState.vehicleInfo,
                        gridState = dashboardGridState
                    )

                    Fuel -> FuelScreen(
                        vehicleInfo = uiState.vehicleInfo,
                        gridState = fuelGridState,
                        isDemoAccount = uiState.isDemoAccount
                    )

                    Sensor -> SensorScreen(
                        vehicleInfo = uiState.vehicleInfo,
                        gridState = sensorGridState
                    )

                    Map -> MapScreen(
                        phoneInfo = uiState.phoneInfo,
                        vehicleInfo = uiState.vehicleInfo,
                        isDemoAccount = uiState.isDemoAccount
                    )

                    Trips -> TripRoot(
                        lazyListState = tripLazyListState,
                        vehicleId = uiState.vehicleInfo.vehicleId,
                        isDemoAccount = uiState.isDemoAccount
                    )
                }
            }
        }

        SettingsRoot(
            settingsController = settingsController,
            selectedVehicleId = uiState.vehicleInfo.vehicleId,
            isDemoAccount = uiState.isDemoAccount,
            onVehicleSelect = onVehicleSelect
        )
    }
}