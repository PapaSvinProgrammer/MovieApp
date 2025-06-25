package com.example.network.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String?,
    val slug: String?
)