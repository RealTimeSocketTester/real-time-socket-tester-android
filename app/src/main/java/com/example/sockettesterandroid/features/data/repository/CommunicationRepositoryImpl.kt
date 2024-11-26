package com.example.sockettesterandroid.features.data.repository

import com.example.sockettesterandroid.features.data.datasource.CommunicationDataSource
import com.example.sockettesterandroid.features.domain.repository.CommunicationRepository
import javax.inject.Inject

class CommunicationRepositoryImpl @Inject() constructor(private val communicationDataSource: CommunicationDataSource) :
    CommunicationRepository {
}