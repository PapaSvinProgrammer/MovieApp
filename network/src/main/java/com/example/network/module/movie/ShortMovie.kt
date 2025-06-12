package com.example.network.module.movie

import kotlinx.serialization.Serializable

@Serializable
data class ShortMovie(
    val id: Int = 0,
    val name: String? = null,
    val alternativeName: String? = null,
    val rating: Float? = null,
    val description: String? = null,
    val enProfession: String? = null
)