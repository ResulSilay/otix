package com.otix.mobile.feature.trip.presentation.screen.trip

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otix.core.compose.recomposeHighlighter
import com.otix.core.extension.isNoneText
import com.otix.core.extension.orZero
import com.otix.core.extension.toDateFormat
import com.otix.core.navigation.route.TripDetail
import com.otix.core.resources.alias.Draw
import com.otix.core.resources.alias.Str
import com.otix.core.resources.date.DateFormat
import com.otix.core.ui.component.OtixEmptyState
import com.otix.core.ui.provider.LocalNavigator
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.feature.trip.presentation.component.TripCard

@Composable
internal fun TripScreen(
    uiState: TripContract.State,
    lazyListState: LazyListState,
    isDemoAccount: Boolean,
) {
    val navigator = LocalNavigator.current
    val strings = LocalStrings.current
    val tripList = uiState.tripList

    if (tripList.isEmpty()) {
        OtixEmptyState(
            modifier = Modifier.padding(bottom = 64.dp),
            iconResId = Draw.ic_storage,
            iconSize = 48.dp,
            text = strings[Str.trip_empty_state_text]
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .recomposeHighlighter(),
        state = lazyListState
    ) {
        itemsIndexed(items = tripList) { _, trip ->
            TripCard(
                dateTitle = trip.info.startTime.toDateFormat(
                    pattern = DateFormat.FullDateTimeWithMonthName.pattern
                ),
                distanceTitle = strings[Str.trip_card_distance_title],
                distanceValueText = "${trip.info.distanceMeters.isNoneText()} km",
                fuelTitle = strings[Str.trip_card_fuel_title],
                fuelValueText = "${trip.info.fuelUsedLiters.isNoneText()} l",
                startAddressText = trip.info.routePoints.firstOrNull()?.address.isNoneText(),
                endAddressText = trip.info.routePoints.lastOrNull()?.address.isNoneText(),
                onClick = {
                    navigator.navigate(
                        route = TripDetail(
                            tripId = trip.id.orZero(),
                            isDemoAccount = isDemoAccount
                        )
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(height = 16.dp))
        }
    }
}