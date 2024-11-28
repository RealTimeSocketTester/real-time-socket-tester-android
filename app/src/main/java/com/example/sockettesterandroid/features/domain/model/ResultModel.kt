package com.example.sockettesterandroid.features.domain.model

data class ResultModel<T>(
    val isSuccess: Boolean = false,
    val message: String = "",
    val data: T? = null,
)