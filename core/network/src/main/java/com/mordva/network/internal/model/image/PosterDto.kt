package com.mordva.network.internal.model.image

import kotlinx.serialization.Serializable

@Serializable
internal data class PosterDto(
    val id: String?,
    val height: Int?,
    val width: Int?,
    val url: String?,
    val previewUrl: String?,
)