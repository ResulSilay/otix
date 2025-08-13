package com.otix.mobile.feature.trip.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otix.core.compose.recomposeHighlighter
import com.otix.core.extension.isNoneText
import com.otix.core.extension.toDateFormat
import com.otix.core.navigation.route.TripMap
import com.otix.core.resources.alias.Str
import com.otix.core.resources.date.DateFormat
import com.otix.core.ui.component.OtixBarChart
import com.otix.core.ui.component.OtixButton
import com.otix.core.ui.component.OtixLineChart
import com.otix.core.ui.provider.LocalNavigator
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.core.ui.component.InfoHeader
import com.otix.mobile.feature.trip.presentation.component.TripLocationCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TripDetailScreen(
    uiState: TripDetailContract.State
) {
    val navigator = LocalNavigator.current
    val strings = LocalStrings.current

    Scaffold(
        modifier = Modifier.recomposeHighlighter(),
        bottomBar = {
            OtixButton(text = strings[Str.trip_detail_map_routes_button_text]) {
                navigator.navigate(
                    route = TripMap(
                        tripId = uiState.tripId,
                        isDemoAccount = uiState.isDemoAccount
                    )
                )
            }
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddings.calculateBottomPadding())
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.trip?.info?.startTime?.toDateFormat(
                    pattern = DateFormat.FullDateTimeWithMonthName.pattern
                ).orEmpty(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            TripLocationCard(
                startAddressText = uiState.trip?.info?.routePoints?.firstOrNull()?.address.isNoneText(),
                endAddressText = uiState.trip?.info?.routePoints?.lastOrNull()?.address.isNoneText(),
            )

            InfoHeader(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = strings[Str.trip_detail_fuel_line_chart_title],
            )

            OtixLineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                height = 150.dp,
                values = uiState.trip?.info?.fuelPercentages?.map { item ->
                    Pair(
                        item.timestamp,
                        item.value
                    )
                }.orEmpty(),
                emptyStateText = strings[Str.trip_chart_empty_state_text]
            )

            InfoHeader(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = strings[Str.trip_detail_fuel_bar_chart_title]
            )

            OtixBarChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                height = 150.dp,
                values = uiState.trip?.info?.fuelPercentages?.map { item ->
                    Pair(
                        first = item.timestamp,
                        second = item.value
                    )
                }.orEmpty(),
                emptyStateText = strings[Str.trip_chart_empty_state_text]
            )

            InfoHeader(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = strings[Str.trip_detail_battery_line_chart_title],
            )

            OtixLineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                height = 150.dp,
                values = uiState.trip?.info?.batteryPercentages?.map { item ->
                    Pair(
                        first = item.timestamp,
                        second = item.value
                    )
                }.orEmpty(),
                emptyStateText = strings[Str.trip_chart_empty_state_text]
            )

            InfoHeader(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = strings[Str.trip_detail_battery_bar_chart_title]
            )

            OtixBarChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                height = 150.dp,
                values = uiState.trip?.info?.batteryPercentages?.map { item ->
                    Pair(
                        first = item.timestamp,
                        second = item.value
                    )
                }.orEmpty(),
                emptyStateText = strings[Str.trip_chart_empty_state_text]
            )
        }
    }
}