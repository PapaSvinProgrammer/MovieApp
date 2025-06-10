package com.example.network.module.movie

import kotlinx.serialization.Serializable

@Serializable
data class ShortMovie(
    val id: Int? = null,
    val name: String? = null,
    val rating: Float? = null
)