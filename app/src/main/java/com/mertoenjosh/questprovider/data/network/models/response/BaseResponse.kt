package com.mertoenjosh.questprovider.data.network.models.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    val status: String,
    val results: Int,
    val requestedAt: String,
    val data: T
)