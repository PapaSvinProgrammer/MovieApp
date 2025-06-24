package com.example.network.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Budget(
    val value: Int?,
    val currency: String?,
)