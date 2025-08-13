package com.otix.mobile.feature.settings.presentation.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
class SettingsController {

    var isVisible by mutableStateOf(false)

    suspend fun open(sheetState: SheetState) {
        isVisible = true
        sheetState.show()
    }

    suspend fun close(sheetState: SheetState) {
        sheetState.hide()
        isVisible = false
    }
}
