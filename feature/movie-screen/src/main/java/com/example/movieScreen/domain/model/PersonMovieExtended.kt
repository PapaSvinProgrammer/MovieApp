package com.example.movieScreen.domain.model

internal data class PersonMovieExtended(
    val id: Int = 0,
    val name: String?,
    val enName: String?,
    val photo: String?,
    val birthday: String?,
    val death: String?,
    val age: Int?,
    val description: String?,
    val enProfession: String?,
    val profession: String?
)
