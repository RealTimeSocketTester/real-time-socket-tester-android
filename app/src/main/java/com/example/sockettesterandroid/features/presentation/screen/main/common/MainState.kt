package com.example.sockettesterandroid.features.presentation.screen.main.common

import com.example.sockettesterandroid.features.domain.model.SocketResultModel

data class MainState(
    val socketState: MainViewModel.SocketState = MainViewModel.SocketState.NotConnected,
    val ipHostValue: String = "",
    val portValue: String = "",
    val messageValue: String = "",
    val isIpHostError: Boolean = false,
    val isPortError: Boolean = false,
    val isMessageError: Boolean = false,
    val ipHostErrorMessage: String = "",
    val portErrorMessage: String = "",
    val messageErrorMessage: String = "",
    val resultList: List<SocketResultModel> = emptyList(),
)