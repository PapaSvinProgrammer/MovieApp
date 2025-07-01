package com.example.network.internal.model.category

import com.example.network.internal.model.image.PosterDto
import kotlinx.serialization.Serializable

@Serializable
internal data class WatchabilityItemDto(
    val name: String? = null,
    val logo: PosterDto? = null,
    val url: String? = null,
)