package com.example.network.internal.model.image

import kotlinx.serialization.Serializable

@Serializable
internal data class PosterDto(
    val url: String?,
    val previewUrl: String?,
)