package com.example.network.module.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class Votes(
    val kp: Int?,
    val imdb: Int?,
    val filmCritics: Int?,
    val russianFilmCritics: Int?,
    val await: Int?
)