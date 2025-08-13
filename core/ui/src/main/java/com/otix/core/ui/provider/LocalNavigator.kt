package com.otix.core.ui.provider

import androidx.compose.runtime.staticCompositionLocalOf
import com.otix.core.navigation.navigator.Navigator

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("Navigator not provided")
}