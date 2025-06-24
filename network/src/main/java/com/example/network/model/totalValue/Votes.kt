package com.example.network.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Votes(
    val kp: Int? = null,
    val imdb: Int? = null,
    val filmCritics: Int? = null,
    val russianFilmCritics: Int? = null,
    val await: Int? = null
)