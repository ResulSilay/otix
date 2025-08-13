package com.otix.core.navigation.navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface Navigator {

    fun setBackStack(stack: NavBackStack)

    fun navigate(route: NavKey)

    fun navigate(route: NavKey, popUpToRoute: NavKey, inclusive: Boolean)

    fun onBack()
}