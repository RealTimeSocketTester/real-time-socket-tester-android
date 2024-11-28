package com.example.sockettesterandroid.features.domain.mapper.entity

import com.example.sockettesterandroid.features.domain.entity.SocketResultEntity
import com.example.sockettesterandroid.features.domain.model.SocketResultModel

fun SocketResultEntity.toSocketResultModel(): SocketResultModel {
    return SocketResultModel(
        this.data,
        this.date,
    )
}