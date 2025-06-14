package com.example.network.module.category

import com.example.network.module.image.Poster
import kotlinx.serialization.Serializable

@Serializable
data class WatchabilityItem(
    val name: String? = null,
    val logo: Poster? = null,
    val url: String? = null,
)