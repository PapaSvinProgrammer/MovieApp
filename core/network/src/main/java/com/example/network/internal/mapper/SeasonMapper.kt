package com.example.network.internal.mapper

import com.example.model.season.Episode
import com.example.model.season.Season
import com.example.network.internal.model.season.EpisodeDto
import com.example.network.internal.model.season.SeasonDto

internal fun EpisodeDto.toDomain(): Episode = Episode(
    number = this.number,
    name = this.name,
    enName = this.enName,
    airDate = this.airDate,
    description = this.description,
    enDescription = this.enDescription,
    still = this.stillDto?.toDomain()
)

internal fun SeasonDto.toDomain(): Season = Season(
    movieId = this.movieId,
    number = this.number,
    name = this.name,
    enName = this.enName,
    episodesCount = this.episodesCount,
    airDate = this.airDate,
    episodes = this.episodeDto.map { it.toDomain() }
)