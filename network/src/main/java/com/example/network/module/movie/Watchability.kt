package com.example.network.module.movie

import com.example.network.module.category.WatchabilityItem
import kotlinx.serialization.Serializable

@Serializable
data class Watchability(
    val items: List<WatchabilityItem> = listOf()
)