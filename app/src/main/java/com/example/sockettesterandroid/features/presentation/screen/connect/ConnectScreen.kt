package com.example.sockettesterandroid.features.presentation.screen.connect

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sockettesterandroid.core.utils.AppUtility
import com.example.sockettesterandroid.features.presentation.screen.connect.common.ConnectViewModel
import androidx.navigation.compose.rememberNavController
import com.example.sockettesterandroid.features.presentation.components.AppButton
import com.example.sockettesterandroid.features.presentation.components.AppTextField
import com.example.sockettesterandroid.features.presentation.screen.main.common.MainViewModel
import com.example.sockettesterandroid.ui.theme.DeepCharcoal
import com.example.sockettesterandroid.ui.theme.Green
import com.example.sockettesterandroid.ui.theme.LightGray
import com.example.sockettesterandroid.ui.theme.PrimaryAccent
import com.example.sockettesterandroid.ui.theme.TextBlueColor
import com.example.sockettesterandroid.ui.theme.TextGrayColor
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun ConnectScreen(
    navController: NavController,
    viewModel: ConnectViewModel = hiltViewModel(),
) {
    val state by remember { viewModel.state }
    val ipTextChange = remember<(String) -> Unit> {
        {
            viewModel.onIpHostTextChanged(it)
        }
    }
    val portTextChange = remember<(String) -> Unit> {
        {
            viewModel.onPortTextChanged(it)
        }
    }
    val onConnect = remember { { viewModel.onConnect() } }

    Scaffold(
        content = {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                Box(
                    modifier = Modifier.background(color = DeepCharcoal)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                vertical = 20.dp,
                                horizontal = 30.dp,
                            ),
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1.3f)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                "SOCKET PLAYGROUND",
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    color = PrimaryAccent,
                                    fontWeight = FontWeight.W600,
                                ),
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "Real-time Socket Listener",
                                style = TextStyle(
                                    color = LightGray,
                                    fontSize = 18.sp,
                                ),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth()
                        ) {
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start,
                                ) {
                                    Checkbox(
                                        enabled = state.status == ConnectViewModel.Status.Idle,
                                        modifier = Modifier.scale(1.3f),
                                        checked = state.isWebSocketChecked,
                                        onCheckedChange = { isChecked ->
                                            viewModel.updateCheckState(
                                                isChecked,
                                            )
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Green,
                                        )
                                    )
                                    Text(
                                        "Is Websocket",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = LightGray,
                                            fontWeight = FontWeight.W600,
                                        ),
                                    )
                                }
                                Row {
                                    AppTextField(
                                        label = if (!state.isWebSocketChecked) "HOST / IP" else "HOST",
                                        value = state.ipHostValue,
                                        isError = state.isIpHostError,
                                        onValueChange = ipTextChange,
                                        modifier = Modifier.weight(1.0f),
                                        isEnable = state.status == ConnectViewModel.Status.Idle,
                                    )
                                    if (!state.isWebSocketChecked)
                                        Spacer(modifier = Modifier.width(10.dp))
                                    if (!state.isWebSocketChecked)
                                        AppTextField(
                                            label = "PORT",
                                            value = state.portValue,
                                            isError = state.isPortError,
                                            onValueChange = portTextChange,
                                            modifier = Modifier.width(100.dp),
                                            isEnable = state.status == ConnectViewModel.Status.Idle,
                                        )
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                AppButton(
                                    isLoading = state.status == ConnectViewModel.Status.Connecting,
                                    isEnable = true,
                                    text = "CONNECT",
                                    onClick = onConnect,
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ConnectScreenPreview() {
    val navController = rememberNavController()
    ConnectScreen(navController)
}