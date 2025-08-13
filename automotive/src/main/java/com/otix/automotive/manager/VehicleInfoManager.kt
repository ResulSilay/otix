package com.otix.automotive.manager

interface VehicleInfoManager<T> {

    fun registerListener(callback: (T) -> Unit)

    fun unregisterListener()
}