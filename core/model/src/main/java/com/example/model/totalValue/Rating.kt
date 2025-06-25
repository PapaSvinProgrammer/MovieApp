package com.example.network.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val kp: Float? = null,
    val imdb: Float? = null,
    val filmCritics: Float? = null,
    val russianFilmCritics: Float? = null
)