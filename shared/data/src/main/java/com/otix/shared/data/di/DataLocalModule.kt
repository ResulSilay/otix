package com.otix.shared.data.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.otix.shared.data.local.OtixDatabase
import com.otix.shared.data.local.mapper.property.PropertyMapper
import com.otix.shared.data.local.mapper.property.PropertyMapperImpl
import com.otix.shared.data.local.mapper.trip.TripMapper
import com.otix.shared.data.local.mapper.trip.TripMapperImpl
import com.otix.shared.data.local.mapper.vehicle.VehicleMapper
import com.otix.shared.data.local.mapper.vehicle.VehicleMapperImpl
import com.otix.shared.data.local.repository.PropertyRepositoryImpl
import com.otix.shared.data.local.repository.TripRepositoryImpl
import com.otix.shared.data.local.repository.VehicleRepositoryImpl
import com.otix.shared.data.local.source.property.PropertyLocalDataSource
import com.otix.shared.data.local.source.property.PropertyLocalDataSourceImpl
import com.otix.shared.data.local.source.trip.TripLocalDataSource
import com.otix.shared.data.local.source.trip.TripLocalDataSourceImpl
import com.otix.shared.data.local.source.vehicle.VehicleLocalDataSource
import com.otix.shared.data.local.source.vehicle.VehicleLocalDataSourceImpl
import com.otix.shared.domain.local.repository.PropertyRepository
import com.otix.shared.domain.local.repository.TripRepository
import com.otix.shared.domain.local.repository.VehicleRepository
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataLocalModule = module {

    single {
        OtixDatabase(
            driver = AndroidSqliteDriver(
                schema = OtixDatabase.Schema,
                context = get(),
                name = "otix.db"
            )
        )
    }

    single<Json> {
        Json { ignoreUnknownKeys = true }
    }

    single<VehicleLocalDataSource> {
        VehicleLocalDataSourceImpl(
            database = get(),
            json = get()
        )
    }

    single<PropertyLocalDataSource> {
        PropertyLocalDataSourceImpl(
            database = get()
        )
    }

    single<TripLocalDataSource> {
        TripLocalDataSourceImpl(
            database = get(),
            json = get()
        )
    }

    single<VehicleRepository> {
        VehicleRepositoryImpl(
            localDataSource = get(),
            vehicleMapper = get()
        )
    }

    single<PropertyRepository> {
        PropertyRepositoryImpl(
            localDataSource = get(),
            propertyMapper = get()
        )
    }

    single<TripRepository> {
        TripRepositoryImpl(
            localDataSource = get(),
            tripMapper = get()
        )
    }

    single<VehicleMapper> {
        VehicleMapperImpl()
    }

    single<PropertyMapper> {
        PropertyMapperImpl()
    }

    single<TripMapper> {
        TripMapperImpl()
    }
}
