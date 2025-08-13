package com.otix.core.resources.di

import com.otix.core.resources.strings.Strings
import com.otix.core.resources.strings.StringsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val stringsModule = module {

    single<Strings> { StringsImpl(context = androidContext()) }
}