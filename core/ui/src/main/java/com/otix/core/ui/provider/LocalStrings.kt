package com.otix.core.ui.provider

import androidx.compose.runtime.staticCompositionLocalOf
import com.otix.core.resources.strings.Strings

val LocalStrings = staticCompositionLocalOf<Strings> {
    error("Strings not provided")
}