package com.otix.app

import android.app.Application
import com.otix.core.location.di.locationModule
import com.otix.core.navigation.di.navigationModule
import com.otix.core.resources.di.stringsModule
import com.otix.mobile.feature.fuel.presentation.di.fuelModule
import com.otix.mobile.feature.settings.presentation.di.settingsModule
import com.otix.mobile.feature.trip.presentation.di.tripModule
import com.otix.shared.data.di.dataLocalModule
import com.otix.shared.domain.di.domainModule
import com.otix.shared.presentation.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OtixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OtixApplication)
            modules(
                navigationModule,
                stringsModule,
                locationModule,
                sharedModule,
                fuelModule,
                tripModule,
                settingsModule,
                dataLocalModule,
                domainModule
            )
        }
    }
}