package com.example.sockettesterandroid.features.presentation.screen.main.common

data class MainState(
    val state: MainViewModel.SocketState = MainViewModel.SocketState.NotConnected,
    val ipHostValue: String = "",
    val portValue: String = "",
    val messageValue: String = "",
    val isIpHostError: Boolean = false,
    val isPortError: Boolean = false,
    val messageError: Boolean = false,
    val ipHostErrorMessage: String = "",
    val portErrorMessage: String = "",
    val messageErrorMessage: String = "",
)