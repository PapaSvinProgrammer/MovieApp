package com.mordva.network.internal.model.movie

import kotlinx.serialization.Serializable

@Serializable
internal data class ShortMovieDto(
    val id: Int = 0,
    val name: String? = null,
    val alternativeName: String? = null,
    val rating: Float? = null,
    val description: String? = null,
    val enProfession: String? = null
)