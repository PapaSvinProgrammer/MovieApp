package com.example.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class ReleaseYearsDto(
    val start: Int? = null,
    val end: Int? = null
)