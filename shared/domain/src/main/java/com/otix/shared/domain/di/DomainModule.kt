package com.otix.shared.domain.di

import com.otix.shared.domain.local.usecase.GetLastVehicleUseCase
import com.otix.shared.domain.local.usecase.GetPropertyUseCase
import com.otix.shared.domain.local.usecase.GetTripListUseCase
import com.otix.shared.domain.local.usecase.GetTripUseCase
import com.otix.shared.domain.local.usecase.GetVehicleUseCase
import com.otix.shared.domain.local.usecase.GetVehiclesUseCase
import com.otix.shared.domain.local.usecase.RemoveVehicleUseCase
import com.otix.shared.domain.local.usecase.SavePropertyUseCase
import com.otix.shared.domain.local.usecase.SaveTripUseCase
import com.otix.shared.domain.local.usecase.SaveVehicleUseCase
import com.otix.shared.domain.manager.TripManager
import com.otix.shared.domain.manager.TripManagerImpl
import org.koin.dsl.module

val domainModule = module {

    single { GetVehicleUseCase(repository = get()) }

    single { GetLastVehicleUseCase(repository = get()) }

    single { GetVehiclesUseCase(repository = get()) }

    single { RemoveVehicleUseCase(repository = get()) }

    single { SaveVehicleUseCase(repository = get()) }

    single { GetPropertyUseCase(repository = get()) }

    single { SavePropertyUseCase(repository = get()) }

    single { GetTripUseCase(repository = get()) }

    single { GetTripListUseCase(repository = get()) }

    single { SaveTripUseCase(repository = get()) }

    single<TripManager> { TripManagerImpl() }
}
