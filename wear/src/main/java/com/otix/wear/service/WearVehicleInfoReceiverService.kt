package com.otix.wear.service

import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService
import com.otix.shared.domain.local.usecase.SaveVehicleUseCase
import com.otix.shared.domain.model.Vehicle
import com.otix.shared.domain.model.VehicleInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject

class WearVehicleInfoReceiverService : WearableListenerService() {

    private val json: Json by inject()
    private val saveCar: SaveVehicleUseCase by inject()

    override fun onDataChanged(events: DataEventBuffer) {
        events.forEach { event ->
            if (event.type != DataEvent.TYPE_CHANGED) return@forEach
            if (event.dataItem.uri.path != VEHICLE_INFO_URI_PATH) return@forEach

            val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
            val carInfoJson = dataMap.getString(VEHICLE_INFO_DATA_KEY)

            if (!carInfoJson.isNullOrBlank()) {
                runCatching {
                    val vehicleInfo = json.decodeFromString<VehicleInfo>(carInfoJson)

                    CoroutineScope(Dispatchers.IO).launch {
                        saveCar(vehicleInfo)
                    }
                }.onFailure { error ->
                    error.printStackTrace()
                }
            }
        }
    }

    private suspend fun saveCar(vehicleInfo: VehicleInfo) {
        saveCar(
            vehicle = Vehicle(
                id = vehicleInfo.vehicleId,
                info = vehicleInfo
            )
        )
    }

    private companion object {

        const val VEHICLE_INFO_URI_PATH = "/vehicle-info"
        const val VEHICLE_INFO_DATA_KEY = "vehicleInfoJson"
    }
}
