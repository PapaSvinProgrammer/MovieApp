package com.example.network.internal.model.movie

import kotlinx.serialization.Serializable

@Serializable
internal data class FactDto(
    val value: String,
    val type: String? = null,
    val spoiler: Boolean? = null,
)