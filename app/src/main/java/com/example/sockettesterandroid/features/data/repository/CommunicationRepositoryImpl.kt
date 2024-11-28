package com.example.sockettesterandroid.features.data.repository

import com.example.sockettesterandroid.features.data.datasource.CommunicationDataSource
import com.example.sockettesterandroid.features.domain.entity.ResultEntity
import com.example.sockettesterandroid.features.domain.repository.CommunicationRepository
import javax.inject.Inject

class CommunicationRepositoryImpl @Inject() constructor(
    private val communicationDataSource: CommunicationDataSource,
) : CommunicationRepository {

    override suspend fun startConnection(
        ipAddress: String,
        port: String,
        onConnected: (() -> Unit)?,
        onResult: ((String) -> Unit)?,
        onDone: (() -> Unit)?
    ) {
        communicationDataSource.startConnection(
            ipAddress = ipAddress,
            port = port,
            onConnected = onConnected,
            onDone = onDone,
            onResult = onResult,
        )
    }

    override suspend fun stopConnection() {
        communicationDataSource.stopConnection()
    }

    override suspend fun sendData(data: String) {
        communicationDataSource.sendData(data)
    }

}