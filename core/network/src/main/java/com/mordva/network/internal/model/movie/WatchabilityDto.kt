package com.mordva.network.internal.model.movie

import com.mordva.network.internal.model.category.WatchabilityItemDto
import kotlinx.serialization.Serializable

@Serializable
internal data class WatchabilityDto(
    val items: List<WatchabilityItemDto>? = null
)