package com.example.network.module.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val kp: Float?,
    val imdb: Float?,
    val filmCritics: Float?,
    val russianFilmCritics: Float?
)