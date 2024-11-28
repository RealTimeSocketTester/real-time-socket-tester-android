package com.example.sockettesterandroid.features.data.datasource

import com.example.sockettesterandroid.features.domain.entity.ResultEntity

interface CommunicationDataSource {
    suspend fun startConnection(
        ipAddress: String,
        port: String,
        onConnected: (() -> Unit)? = null,
        onError: ((String) -> Unit)? = null,
        onResult: ((String) -> Unit)? = null,
        onDone: (() -> Unit)? = null,
    )

    suspend fun stopConnection()

    suspend fun sendData(data: String)
}