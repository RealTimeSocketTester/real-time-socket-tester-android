package com.example.sockettesterandroid.features.domain.entity

import java.time.LocalDateTime

data class SocketResultEntity(
    val data: String = "",
    val date: LocalDateTime? = null,
)