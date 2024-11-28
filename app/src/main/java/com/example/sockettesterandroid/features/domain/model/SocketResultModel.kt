package com.example.sockettesterandroid.features.domain.model

import java.time.LocalDateTime

data class SocketResultModel(
    val data: String = "",
    val date: LocalDateTime? = null,
)