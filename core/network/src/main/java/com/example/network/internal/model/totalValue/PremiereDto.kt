package com.example.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class PremiereDto(
    val world: String?,
    val dvd: String?
)