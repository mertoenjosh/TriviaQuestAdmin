package com.mertoenjosh.questprovider.domain.models

data class BaseDomainModel <T> (
    val error: Boolean,
    val message: String,
    val data: T?
)