package com.otix.core.resources.strings

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface Strings {

    operator fun get(@StringRes id: Int): String

    operator fun get(@StringRes id: Int, vararg formatArgs: Any): String

    fun getArray(@ArrayRes id: Int): List<String>
}
