package com.example.network.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Audience(
    val count: Int?,
    val country: String?,
)