package com.example.network.internal.model.movie

import com.example.network.internal.model.category.WatchabilityItemDto
import kotlinx.serialization.Serializable

@Serializable
internal data class WatchabilityDto(
    val items: List<WatchabilityItemDto>? = null
)