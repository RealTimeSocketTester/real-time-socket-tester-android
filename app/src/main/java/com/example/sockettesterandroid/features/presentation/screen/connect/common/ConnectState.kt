package com.example.sockettesterandroid.features.presentation.screen.connect.common

data class ConnectState(
    val isWebSocketChecked : Boolean = false,
    val ipHostValue: String = "",
    val portValue: String = "",
    val messageValue: String = "",
    val isIpHostError: Boolean = false,
    val isPortError: Boolean = false,
    val isMessageError: Boolean = false,
    val ipHostErrorMessage: String = "",
    val portErrorMessage: String = "",
    val messageErrorMessage: String = "",
)