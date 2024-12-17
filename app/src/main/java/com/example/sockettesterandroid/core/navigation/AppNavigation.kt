package com.example.sockettesterandroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sockettesterandroid.features.presentation.screen.connect.ConnectScreen
import com.example.sockettesterandroid.features.presentation.screen.main.MainScreen
import com.example.sockettesterandroid.features.presentation.screen.splash.SplashScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.CONNECT) {
        composable(route = Routes.SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(route = Routes.MAIN) {
            MainScreen(navController = navController)
        }
        composable(route = Routes.CONNECT) {
            ConnectScreen(navController = navController)
        }
    }
}