package com.otix.core.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiContract.State, I : UiContract.Intent, E : UiContract.Effect>(
    initialState: S,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _effect = Channel<E>(Channel.UNLIMITED)
    val effect = _effect.receiveAsFlow()

    protected abstract suspend fun reduce(intent: I)

    protected fun setState(reducer: S.() -> S) {
        _uiState.update { state -> state.reducer() }
    }

    protected fun sendEffect(builder: () -> E) {
        viewModelScope.launch {
            _effect.send(element = builder())
        }
    }

    fun onIntent(intent: I) {
        viewModelScope.launch {
            reduce(intent = intent)
        }
    }
}
