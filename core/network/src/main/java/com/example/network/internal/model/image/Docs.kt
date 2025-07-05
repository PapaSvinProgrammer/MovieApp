package com.example.network.internal.model.image

import kotlinx.serialization.Serializable

@Serializable
internal data class Docs <T> (
    val docs: List<T> = listOf(),
    val total: Int = 0
)