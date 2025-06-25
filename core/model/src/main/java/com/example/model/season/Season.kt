package com.example.network.model.season

import kotlinx.serialization.Serializable

@Serializable
data class Season(
    val movieId: Int = 0,
    val number: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val episodesCount: Int? = null,
    val airDate: String? = null,
    val episodes: List<Episode> = listOf()
)