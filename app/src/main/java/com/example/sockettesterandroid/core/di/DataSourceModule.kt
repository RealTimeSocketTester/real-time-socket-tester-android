package com.example.sockettesterandroid.core.di

import android.app.Application
import android.content.Context
import com.example.sockettesterandroid.features.data.datasource.CommunicationDataSource
import com.example.sockettesterandroid.features.data.datasource.impl.CommunicationDataSourceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCommunicationDataSource(): CommunicationDataSource {
        return CommunicationDataSourceImpl()
    }
}