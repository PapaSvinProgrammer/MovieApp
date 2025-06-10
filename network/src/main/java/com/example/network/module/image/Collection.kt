package com.example.network.module.image

import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val slug: String?,
    val category: String?,
    val cover: Poster?,
    val moviesCount: Int?,
    val name: String?,
    val id: String,
)