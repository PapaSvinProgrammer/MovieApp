package com.mordva.model.image

data class MovieImageParams(
    val movieId: Int,
    val page: Int = 1,
    val types: Set<ImageType> = setOf()
)