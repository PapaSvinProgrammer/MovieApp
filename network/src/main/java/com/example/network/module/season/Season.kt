package com.example.network.module.season

import kotlinx.serialization.Serializable

@Serializable
data class Season(
    val movieId: Int?,
    val number: Int?,
    val name: String?,
    val enName: String?,
    val episodesCount: Int?,
    val airDate: String?,
    val episodes: List<Episode>
)