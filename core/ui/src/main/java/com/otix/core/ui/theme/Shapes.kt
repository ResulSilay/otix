package com.otix.core.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable

internal val shapes: Shapes

    @Composable
    get() = MaterialTheme.shapes.copy(
        large = CircleShape
    )