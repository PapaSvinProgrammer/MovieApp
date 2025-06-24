package com.example.network.model.movie

import com.example.network.model.category.WatchabilityItem
import kotlinx.serialization.Serializable

@Serializable
data class Watchability(
    val items: List<WatchabilityItem> = listOf()
)