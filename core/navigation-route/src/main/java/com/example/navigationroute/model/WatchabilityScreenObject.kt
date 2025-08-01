package com.example.navigationroute.model

import kotlinx.serialization.Serializable

@Serializable
data class WatchabilityScreenObject(
    val items: List<WatchabilityItemScreenObject>
)

@Serializable
data class WatchabilityItemScreenObject(
    val name: String?,
    val logo: PosterScreenObject?,
    val url: String?,
)

@Serializable
data class PosterScreenObject(
    val url: String?,
    val previewUrl: String?,
)
