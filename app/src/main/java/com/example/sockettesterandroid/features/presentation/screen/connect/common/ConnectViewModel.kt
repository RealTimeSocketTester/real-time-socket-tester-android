package com.example.sockettesterandroid.features.presentation.screen.connect.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sockettesterandroid.features.domain.repository.CommunicationRepository
import com.example.sockettesterandroid.features.presentation.screen.main.common.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(
    private val communicationRepository: CommunicationRepository,
) : ViewModel() {

    sealed class Status {
        data object Idle : Status()
        data object Connecting : Status()
    }

    private val _state = mutableStateOf(ConnectState())
    val state: MutableState<ConnectState> = _state

    fun updateCheckState(isChecked: Boolean) {
        _state.value = state.value.copy(
            isWebSocketChecked = isChecked,
            isPortError = false,
            portValue = "",
        )
    }

    fun onIpHostTextChanged(value: String) {
        _state.value = state.value.copy(
            ipHostValue = value,
            isIpHostError = value.trim().isEmpty() || value.trim().isBlank()
        )
    }

    fun onPortTextChanged(value: String) {
        _state.value = state.value.copy(
            portValue = value,
            isPortError = value.trim().isEmpty() || value.trim().isBlank(),
        )
    }

    fun onConnect() {
        val host = _state.value.ipHostValue
        val port = _state.value.portValue
        val isWebSocket = _state.value.isWebSocketChecked

        var proceed = true

        if (host.trim().isEmpty() || host.trim().isBlank()) {
            _state.value = state.value.copy(
                isIpHostError = true,
            )
            proceed = false
        }

        if (!isWebSocket && (port.trim().isEmpty() || port.trim().isBlank())) {
            _state.value = state.value.copy(
                isPortError = true,
            )
            proceed = false
        }

        if (proceed) {
            viewModelScope.launch {
                _state.value = state.value.copy(
                    status = Status.Connecting,
                    isPortError = false,
                    isIpHostError = false,
                )
            }
        }
    }

}