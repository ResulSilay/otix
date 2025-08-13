package com.otix.automotive.sender

import android.content.Context
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.otix.core.extension.print
import com.otix.core.extension.toJson
import com.otix.shared.domain.model.VehicleInfo

internal class PhoneWearVehicleInfoSender(val context: Context) {

    private val dataClient = Wearable.getDataClient(context)

    fun sendCarDataToWear(vehicleInfo: VehicleInfo) {
        isWearConnected { isConnected ->
            if (!isConnected) {
                return@isWearConnected
            }

            val request = PutDataMapRequest.create(VEHICLE_INFO_URI_PATH).apply {
                dataMap.putString(VEHICLE_INFO_DATA_KEY, vehicleInfo.toJson())
            }.asPutDataRequest().setUrgent()

            dataClient.putDataItem(request).addOnSuccessListener {
                "Data sent to Wear".print()
            }
        }
    }

    private fun isWearConnected(onResult: (Boolean) -> Unit) {
        Wearable.getNodeClient(context).connectedNodes
            .addOnSuccessListener { nodes ->
                onResult(nodes.isNotEmpty())
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    private companion object {

        const val VEHICLE_INFO_URI_PATH = "/vehicle-info"
        const val VEHICLE_INFO_DATA_KEY = "vehicleInfoJson"
    }
}
