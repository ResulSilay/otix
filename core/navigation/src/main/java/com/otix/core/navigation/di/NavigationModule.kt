package com.otix.core.navigation.di

import com.otix.core.navigation.navigator.Navigator
import com.otix.core.navigation.navigator.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {

    single<Navigator> { NavigatorImpl() }
}