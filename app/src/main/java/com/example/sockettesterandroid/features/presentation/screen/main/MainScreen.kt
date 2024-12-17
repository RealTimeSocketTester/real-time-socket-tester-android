package com.example.sockettesterandroid.features.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sockettesterandroid.features.presentation.components.AppTextField
import com.example.sockettesterandroid.features.presentation.screen.main.common.MainViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.sockettesterandroid.core.utils.AppUtility
import com.example.sockettesterandroid.features.presentation.components.AppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
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
    val messageTextChange = remember<(String) -> Unit> {
        {
            viewModel.onMessageTextChanged(it)
        }
    }
    val onConnectOrDisconnect = remember {
        {
            if (state.socketState == MainViewModel.SocketState.Connected) {
                viewModel.onDisconnect()
            } else if (state.socketState == MainViewModel.SocketState.NotConnected) {
                viewModel.onConnect()
            }
        }
    }
    val onSend = remember {
        {
            viewModel.onSend()
        }
    }

    Scaffold(
        modifier = Modifier
            .imePadding(),
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = "Real-Time Socket Listener")
//                },
//                Modifier.background(color = Color.Black)
//            )
//        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                Box(
                    modifier = Modifier.background(
                        brush = AppUtility.getDefaultAppBackground(),
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AppTextField(
                                label = "HOST / IP",
                                value = state.ipHostValue,
                                isError = state.isIpHostError,
                                onValueChange = ipTextChange,
                                modifier = Modifier.weight(1.0f),
                                isEnable = state.socketState == MainViewModel.SocketState.NotConnected,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            AppTextField(
                                label = "PORT",
                                value = state.portValue,
                                isError = state.isPortError,
                                onValueChange = portTextChange,
                                modifier = Modifier.width(100.dp),
                                isEnable = state.socketState == MainViewModel.SocketState.NotConnected,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            AppButton(
                                text = if (state.socketState == MainViewModel.SocketState.NotConnected) {
                                    "Connect"
                                } else "Disconnect",
                                onClick = onConnectOrDisconnect,
                                isLoading = state.socketState == MainViewModel.SocketState.Connecting,
                                modifier = Modifier.width(120.dp),
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AppTextField(
                                isEnable = state.socketState == MainViewModel.SocketState.Connected,
                                label = "Message",
                                value = state.messageValue,
                                isError = state.isMessageError,
                                onValueChange = messageTextChange,
                                modifier = Modifier.weight(1.0f),
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            AppButton(
                                isEnable = state.socketState == MainViewModel.SocketState.Connected,
                                text = "Send",
                                onClick = onSend,
                                modifier = Modifier.width(120.dp),
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            "Results",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(10.dp),
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                items(state.resultList) { item ->
                                    Text(
                                        item.data,
                                        style = TextStyle(
                                            color = Color.White,
                                        ),
                                    )
                                }
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
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController)
}