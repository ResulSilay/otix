package com.otix.core.navigation.navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class NavigatorImpl : Navigator {

    private lateinit var backStack: NavBackStack

    override fun setBackStack(stack: NavBackStack) {
        backStack = stack
    }

    override fun navigate(route: NavKey) {
        backStack.add(element = route)
    }

    override fun navigate(route: NavKey, popUpToRoute: NavKey, inclusive: Boolean) {
        val index = backStack.indexOfLast { it == popUpToRoute }
        if (index != -1) {
            val removeIndex = if (inclusive) index else index + 1
            while (backStack.size > removeIndex) {
                backStack.removeLastOrNull()
            }
        }
        backStack.add(element = route)
    }

    override fun onBack() {
        backStack.removeLastOrNull()
    }
}
