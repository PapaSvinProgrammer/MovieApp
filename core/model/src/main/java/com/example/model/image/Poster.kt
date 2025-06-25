package com.example.network.model.image

import kotlinx.serialization.Serializable

@Serializable
data class Poster(
    val url: String?,
    val previewUrl: String?,
)