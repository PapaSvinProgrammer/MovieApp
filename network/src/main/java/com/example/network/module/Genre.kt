package com.example.network.module

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val name: String,
    val slug: String
)