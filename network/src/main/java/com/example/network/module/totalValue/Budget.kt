package com.example.network.module.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Budget(
    val value: Int?,
    val currency: String?,
)