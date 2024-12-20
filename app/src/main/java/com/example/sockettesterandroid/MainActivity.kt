package com.example.sockettesterandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.sockettesterandroid.core.navigation.AppNavigationGraph
import com.example.sockettesterandroid.ui.theme.BottomBarColor
import com.example.sockettesterandroid.ui.theme.DeepCharcoal
import com.example.sockettesterandroid.ui.theme.SocketTesterAndroidTheme
import com.example.sockettesterandroid.ui.theme.StatusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            SocketTesterAndroidTheme {
                val systemUiController = rememberSystemUiController()

                //Status Bar Color
                systemUiController.setSystemBarsColor(
                    color = DeepCharcoal,
                    darkIcons = false,
                )
                //Bottom Bar color
                systemUiController.setNavigationBarColor(
                    color = DeepCharcoal,
                    darkIcons = false,
                )

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppEntryPoint()
                }
            }
        }
    }
}

@Composable
fun AppEntryPoint() {
    AppNavigationGraph()
}