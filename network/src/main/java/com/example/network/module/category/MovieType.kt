package com.example.network.module.category

import kotlinx.serialization.Serializable

@Serializable
data class MovieType(
    val name: String?,
    val slug: String?
)