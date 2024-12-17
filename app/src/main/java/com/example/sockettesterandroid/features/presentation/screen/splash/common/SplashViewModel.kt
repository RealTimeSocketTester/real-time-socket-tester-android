package com.example.sockettesterandroid.features.presentation.screen.splash.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    sealed class UIEvents {
        data object GoToMainScreen : UIEvents()
        data object GoToConnectScreen : UIEvents()
    }

    private val _uiEvents = MutableSharedFlow<UIEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    fun initialize() {
        viewModelScope.launch {
            delay(3000) // Wait for 2 seconds
            _uiEvents.emit(UIEvents.GoToConnectScreen)
        }
    }
}