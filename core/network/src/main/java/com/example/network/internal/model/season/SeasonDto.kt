package com.example.network.internal.model.season

import kotlinx.serialization.Serializable

@Serializable
internal data class SeasonDto(
    val movieId: Int = 0,
    val number: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val episodesCount: Int? = null,
    val airDate: String? = null,
    val episodes: List<EpisodeDto> = listOf()
)