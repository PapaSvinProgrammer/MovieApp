package com.mordva.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class VotesDto(
    val kp: Int? = null,
    val imdb: Int? = null,
    val filmCritics: Int? = null,
    val russianFilmCritics: Int? = null,
    val await: Int? = null
)