package com.example.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class AudienceDto(
    val count: Int?,
    val country: String?,
)