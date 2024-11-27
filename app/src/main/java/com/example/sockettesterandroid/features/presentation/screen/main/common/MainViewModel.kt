package com.example.sockettesterandroid.features.presentation.screen.main.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sockettesterandroid.features.domain.repository.CommunicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val communicationRepository: CommunicationRepository) :
    ViewModel() {
    sealed class SocketState {
        data object NotConnected : SocketState()
        data object Connected : SocketState()
        data object Connecting : SocketState()
    }

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun onIpHostTextChanged(value: String) {
        if (value.trim().isEmpty() || value.trim().isBlank()) {
            _state.value = state.value.copy(
                ipHostValue = value,
                isIpHostError = true,
            )
        } else {
            _state.value = state.value.copy(
                ipHostValue = value,
                isIpHostError = false,
            )
        }
    }

    fun onPortTextChanged(value: String) {
        if (value.trim().isEmpty() || value.trim().isBlank()) {
            _state.value = state.value.copy(
                isPortError = true,
                portValue = value,
            )
        } else {
            _state.value = state.value.copy(
                isPortError = false,
                portValue = value,
            )
        }
    }

    fun onMessageTextChanged(value: String) {
        if (value.trim().isEmpty() || value.trim().isBlank()) {
            _state.value = state.value.copy(
                messageValue = value,
                isMessageError = true,
            )
        } else {
            _state.value = state.value.copy(
                messageValue = value,
                isMessageError = false,
            )
        }

    }

    fun onConnect() {
        onIpHostTextChanged(_state.value.ipHostValue)
        onPortTextChanged(_state.value.portValue)

        if (_state.value.isPortError || _state.value.isMessageError || _state.value.isIpHostError) {
            return
        }

        _state.value = state.value.copy(
            socketState = SocketState.Connecting,
        )
    }

    fun onDisconnect() {

    }

    fun onSend() {
        onMessageTextChanged(_state.value.messageValue)

        if (_state.value.isMessageError) {
            return
        }
    }
}