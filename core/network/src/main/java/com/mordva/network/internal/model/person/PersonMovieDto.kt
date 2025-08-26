package com.mordva.network.internal.model.person

import kotlinx.serialization.Serializable

@Serializable
internal data class PersonMovieDto(
    val id: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val photo: String? = null,
    val description: String? = null,
    val profession: String? = null,
    val enProfession: String? = null
)