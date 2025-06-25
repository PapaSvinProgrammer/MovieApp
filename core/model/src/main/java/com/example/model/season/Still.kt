package com.example.network.model.season

import kotlinx.serialization.Serializable

@Serializable
data class Still(
    val url: String?,
    val previewUrl: String?,
)