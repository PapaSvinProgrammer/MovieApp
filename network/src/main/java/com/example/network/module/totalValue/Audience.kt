package com.example.network.module.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Audience(
    val count: Int?,
    val country: String?,
)