package com.example.network.module.category

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String?,
    val slug: String?
)