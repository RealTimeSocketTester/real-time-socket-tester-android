package com.example.sockettesterandroid.features.presentation.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sockettesterandroid.core.navigation.Routes
import com.example.sockettesterandroid.core.utils.AppUtility
import com.example.sockettesterandroid.features.presentation.screen.splash.common.SplashViewModel
import com.example.sockettesterandroid.ui.theme.BlueColor
import com.example.sockettesterandroid.ui.theme.TextBlueColor
import com.example.sockettesterandroid.ui.theme.TextGrayColor
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
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = AppUtility.getDefaultAppBackground())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                "SOCKET PLAYGROUND",
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    color = TextBlueColor,
                                    fontWeight = FontWeight.W600,
                                ),
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "Real-time Socket Listener",
                                style = TextStyle(
                                    color = TextGrayColor,
                                    fontSize = 18.sp,
                                ),
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            color = BlueColor,
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}