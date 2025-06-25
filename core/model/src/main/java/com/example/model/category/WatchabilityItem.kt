package com.example.network.model.category

import com.example.network.model.image.Poster
import kotlinx.serialization.Serializable

@Serializable
data class WatchabilityItem(
    val name: String? = null,
    val logo: Poster? = null,
    val url: String? = null,
)