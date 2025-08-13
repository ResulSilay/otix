package com.otix.core.resources.strings

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

internal class StringsImpl(private val context: Context) : Strings {

    override operator fun get(@StringRes id: Int): String = context.getString(id)

    override operator fun get(
        @StringRes id: Int,
        vararg formatArgs: Any
    ): String = context.getString(id, *formatArgs)

    override fun getArray(
        @ArrayRes id: Int
    ): List<String> = context.resources.getStringArray(id).toList()
}
