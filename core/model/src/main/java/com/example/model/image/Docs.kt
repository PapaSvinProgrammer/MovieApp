package com.example.network.model.image

import kotlinx.serialization.Serializable

@Serializable
data class Docs <T> (
    val docs: List<T> = listOf(),
    val total: Int = 0
)