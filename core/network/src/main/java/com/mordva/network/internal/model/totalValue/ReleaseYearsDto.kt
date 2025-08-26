package com.mordva.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class ReleaseYearsDto(
    val start: Int? = null,
    val end: Int? = null
)