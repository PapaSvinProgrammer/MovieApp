package com.mordva.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class PremiereDto(
    val bluray: String? = null,
    val digital: String? = null,
    val dvd: String? = null,
    val russia: String? = null,
    val world: String? = null,
)