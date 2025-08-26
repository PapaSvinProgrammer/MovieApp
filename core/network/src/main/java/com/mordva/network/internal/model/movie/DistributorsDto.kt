package com.mordva.network.internal.model.movie

import kotlinx.serialization.Serializable

@Serializable
internal data class DistributorsDto(
    val distributor: String?,
    val distributorRelease: String?,
)
