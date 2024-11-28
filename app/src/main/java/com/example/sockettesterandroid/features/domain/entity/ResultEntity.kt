package com.example.sockettesterandroid.features.domain.entity

data class ResultEntity<T>(
    val isSuccess: Boolean = false,
    val message: String = "",
    val data: T? = null,
)