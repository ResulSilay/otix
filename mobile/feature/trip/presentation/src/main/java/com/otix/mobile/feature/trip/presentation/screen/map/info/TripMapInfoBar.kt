package com.otix.mobile.feature.trip.presentation.screen.map.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otix.core.extension.isNoneText
import com.otix.core.extension.orZero
import com.otix.core.resources.alias.Str
import com.otix.core.ui.component.OtixHorizontalDivider
import com.otix.core.ui.provider.LocalStrings
import com.otix.mobile.feature.trip.presentation.screen.map.component.TripMapInfoItem
import com.otix.shared.domain.model.TripInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TripMapInfoBar(
    index: Int,
    tripInfo: TripInfo,
    onDismissRequest: () -> Unit
) {
    val strings = LocalStrings.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp
                )
            )
            .combinedClickable(onClick = onDismissRequest::invoke)
    ) {
        TripMapInfoItem(
            title = strings[Str.trip_info_address_title],
            value = tripInfo.routePoints[index].address.isNoneText()
        )

        OtixHorizontalDivider()

        TripMapInfoItem(
            title = strings[Str.trip_info_location_title],
            value = buildString {
                append(tripInfo.routePoints[index].latitude.isNoneText())
                append(", ")
                append(tripInfo.routePoints[index].longitude.isNoneText())
            }
        )

        OtixHorizontalDivider()

        TripMapInfoItem(
            title = strings[Str.trip_info_fuel_percent_title],
            value = "%" + tripInfo.fuelPercentages.getOrNull(
                index = index
            )?.value.orZero().isNoneText()
        )

        OtixHorizontalDivider()

        TripMapInfoItem(
            title = strings[Str.trip_info_battery_percent_title],
            value = "%" + tripInfo.batteryPercentages.getOrNull(
                index = index
            )?.value.orZero().isNoneText()
        )
    }
}