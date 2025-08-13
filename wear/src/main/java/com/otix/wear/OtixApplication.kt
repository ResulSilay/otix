package com.otix.wear

import android.app.Application
import com.otix.core.resources.di.stringsModule
import com.otix.shared.data.di.dataLocalModule
import com.otix.shared.domain.di.domainModule
import com.otix.wear.di.wearModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OtixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OtixApplication)
            modules(
                wearModule,
                dataLocalModule,
                domainModule,
                stringsModule
            )
        }
    }
}