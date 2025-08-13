package com.otix.core.ui.ext

import androidx.compose.ui.Modifier

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else {
        this
    }
}