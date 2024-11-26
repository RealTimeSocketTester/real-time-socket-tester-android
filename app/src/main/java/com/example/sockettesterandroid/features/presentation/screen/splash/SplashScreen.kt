package com.example.sockettesterandroid.features.presentation.screen.splash

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sockettesterandroid.core.navigation.Routes
import com.example.sockettesterandroid.features.presentation.screen.splash.common.SplashViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    viewModel.initialize()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collectLatest { events ->
            when (events) {
                is SplashViewModel.UIEvents.GoToMainScreen -> {
                    navController.popBackStack()
                    navController.navigate(Routes.MAIN)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        content = { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    )
}