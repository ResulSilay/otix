package com.otix.mobile.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.otix.core.location.manager.LocationManager
import com.otix.core.resources.alias.StrArray
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.feature.settings.presentation.screen.SettingsController
import com.otix.mobile.home.page.PageType
import com.otix.mobile.home.page.PageType.Dashboard
import com.otix.mobile.home.page.PageType.Fuel
import com.otix.mobile.home.page.PageType.Sensor
import com.otix.mobile.home.page.PageType.Trips
import com.otix.shared.presentation.shared.SharedContract
import com.otix.shared.presentation.shared.SharedViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen(modifier: Modifier = Modifier, isDemoAccount: Boolean) {
    val context = LocalContext.current
    val strings = LocalStrings.current

    val locationManager: LocationManager = koinInject<LocationManager>()
    val viewModel: SharedViewModel = koinInject<SharedViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val tabs = strings.getArray(id = StrArray.home_tabs)
    val pagerState = rememberPagerState(pageCount = tabs::size)
    val selectedTabIndex = remember { derivedStateOf(pagerState::currentPage) }
    val settingsController = remember { SettingsController() }

    val dashboardGridState = rememberLazyGridState()
    val fuelGridState = rememberLazyGridState()
    val sensorGridState = rememberLazyGridState()
    val tripLazyListState = rememberLazyListState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            locationManager.startLocationUpdates { location ->
                viewModel.onIntent(intent = SharedContract.Intent.SetGpsLocation(location = location))
            }
            locationManager.getLastKnownLocation { location ->
                viewModel.onIntent(intent = SharedContract.Intent.SetGpsLocation(location = location))
            }
        }
    }

    val isShowMenuFab by remember {
        derivedStateOf {
            when (PageType.parse(position = selectedTabIndex.value)) {
                Dashboard -> dashboardGridState.calculateMenuScrollOffset()
                Fuel -> fuelGridState.calculateMenuScrollOffset()
                Sensor -> sensorGridState.calculateMenuScrollOffset()
                else -> false
            }
        }
    }

    val isNavBarVisible by remember {
        derivedStateOf {
            when (PageType.parse(position = selectedTabIndex.value)) {
                Dashboard -> dashboardGridState.calculateNavBarScrollOffset()
                Fuel -> fuelGridState.calculateNavBarScrollOffset()
                Sensor -> sensorGridState.calculateNavBarScrollOffset()
                Trips -> tripLazyListState.calculateNavBarScrollOffset()
                else -> true
            }
        }
    }

    LaunchedEffect(isDemoAccount) {
        if (isDemoAccount) {
            viewModel.onIntent(intent = SharedContract.Intent.LaunchDemoAccount)
        } else {
            viewModel.onIntent(intent = SharedContract.Intent.Init)
        }
    }

    LaunchedEffect(Unit) {
        locationManager.requestPermissions(
            activity = context as Activity,
            permissionLauncher = permissionLauncher
        )
    }

    HomeContent(
        modifier = modifier,
        strings = strings,
        scope = scope,
        pagerState = pagerState,
        uiState = uiState,
        dashboardGridState = dashboardGridState,
        fuelGridState = fuelGridState,
        sensorGridState = sensorGridState,
        tripLazyListState = tripLazyListState,
        selectedTabIndex = selectedTabIndex,
        settingsController = settingsController,
        isNavBarVisible = isNavBarVisible,
        isShowMenuFab = isShowMenuFab,
        onVehicleSelect = { id ->
            viewModel.onIntent(intent = SharedContract.Intent.GetVehicle(id = id))
        }
    )
}

private fun LazyGridState.calculateMenuScrollOffset(): Boolean {
    return firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 100
}

private fun LazyGridState.calculateNavBarScrollOffset(): Boolean {
    return firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset < 40
}

private fun LazyListState.calculateNavBarScrollOffset(): Boolean {
    return firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset < 40
}