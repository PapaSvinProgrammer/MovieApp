package com.mordva.network.internal.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
internal data class RatingDto(
    val kp: Float? = null,
    val imdb: Float? = null,
    val filmCritics: Float? = null,
    val russianFilmCritics: Float? = null
)