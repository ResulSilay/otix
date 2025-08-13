package com.otix.wear.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.otix.core.ui.provider.LocalStrings
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MainRoot(
    viewModel: MainViewModel = koinViewModel()
) {
    val strings = LocalStrings.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.onIntent(intent = MainContract.Intent.GetCar)
    }

    MainScreen(strings = strings, uiState = uiState)
}