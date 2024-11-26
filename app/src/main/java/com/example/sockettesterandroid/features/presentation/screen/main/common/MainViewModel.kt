package com.example.sockettesterandroid.features.presentation.screen.main.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    sealed class SocketState {
        data object NotConnected : SocketState()
        data object Connected : SocketState()
        data object Connecting : SocketState()
    }

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun onIpHostTextChanged(value: String) {
        _state.value = state.value.copy(
            ipHostValue = value,
        )
    }

    fun onPortTextChanged(value: String) {
        _state.value = state.value.copy(
            portValue = value,
        )
    }

    fun onMessageTextChanged(value: String) {
        _state.value = state.value.copy(
            messageValue = value,
        )
    }

    fun onConnect() {

    }

    fun onDisconnect() {

    }

    fun onSend() {

    }
}