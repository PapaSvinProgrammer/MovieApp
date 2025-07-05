package com.example.model.season

import com.example.model.image.Poster

data class Episode(
    val number: Int?,
    val name: String?,
    val enName: String?,
    val airDate: String?,
    val description: String?,
    val enDescription: String?,
    val still: Poster?,
)