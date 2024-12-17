package com.example.sockettesterandroid.features.presentation.screen.connect.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sockettesterandroid.features.domain.repository.CommunicationRepository
import com.example.sockettesterandroid.features.presentation.screen.main.common.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(
    private val communicationRepository: CommunicationRepository,
) : ViewModel() {
    private val _state = mutableStateOf(ConnectState())
    val state: MutableState<ConnectState> = _state

    fun updateCheckState(isChecked: Boolean) {
        _state.value = state.value.copy(isWebSocketChecked = isChecked)
    }

    fun onIpHostTextChanged(value: String) {
        _state.value = state.value.copy(ipHostValue = value)
    }

    fun onPortTextChanged(value: String) {
        _state.value = state.value.copy(portValue = value)
    }


}