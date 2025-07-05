package com.example.network.internal.model.image

import kotlinx.serialization.Serializable

@Serializable
internal data class CollectionDto(
    val slug: String?,
    val category: String?,
    val cover: PosterDto?,
    val moviesCount: Int?,
    val name: String?
)