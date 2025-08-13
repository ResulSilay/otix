package com.otix.core.location.di

import com.otix.core.location.manager.LocationManager
import com.otix.core.location.manager.LocationManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {

    single<LocationManager> {
        LocationManagerImpl(context = androidContext())
    }
}