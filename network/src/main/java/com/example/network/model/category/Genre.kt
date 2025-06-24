package com.example.network.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val name: String?,
    val slug: String?
)