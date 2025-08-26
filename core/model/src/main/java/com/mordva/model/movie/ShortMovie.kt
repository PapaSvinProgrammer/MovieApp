package com.mordva.model.movie

data class ShortMovie(
    val id: Int,
    val name: String?,
    val alternativeName: String?,
    val rating: Float?,
    val description: String?,
    val enProfession: String?
)