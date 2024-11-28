package com.example.sockettesterandroid.features.domain.repository

import com.example.sockettesterandroid.features.domain.entity.ResultEntity
import com.example.sockettesterandroid.features.domain.model.SocketResultModel

interface CommunicationRepository {
    suspend fun startConnection(
        ipAddress: String,
        port: String,
        onConnected: (() -> Unit)? = null,
        onResult: ((SocketResultModel) -> Unit)? = null,
        onDone: (() -> Unit)? = null,
    )

    suspend fun stopConnection()

    suspend fun sendData(data: String)
}