package com.mordva.movieScreen.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonMovieScreenObject(
    val id: Int,
    val description: String?,
    val profession: String?,
    val enProfession: String?
)
