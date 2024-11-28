package com.example.sockettesterandroid.features.data.datasource

import com.example.sockettesterandroid.features.domain.entity.ResultEntity
import com.example.sockettesterandroid.features.domain.entity.SocketResultEntity

interface CommunicationDataSource {
    suspend fun startConnection(
        ipAddress: String,
        port: String,
        onConnected: (() -> Unit)? = null,
        onError: ((String) -> Unit)? = null,
        onResult: ((SocketResultEntity) -> Unit)? = null,
        onDone: (() -> Unit)? = null,
    )

    suspend fun stopConnection()

    suspend fun sendData(data: String)
}