package com.example.navigationroute.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonMovieScreenObject(
    val id: Int,
    val description: String?,
    val profession: String?,
    val enProfession: String?
)
