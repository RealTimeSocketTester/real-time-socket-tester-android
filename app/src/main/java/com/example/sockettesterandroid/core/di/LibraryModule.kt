package com.example.sockettesterandroid.core.di
import com.example.sockettesterandroid.features.data.datasource.CommunicationDataSource
import com.example.sockettesterandroid.features.data.datasource.impl.CommunicationDataSourceImpl
import com.example.sockettesterandroid.features.data.repository.CommunicationRepositoryImpl
import com.example.sockettesterandroid.features.domain.repository.CommunicationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LibraryModule {

}