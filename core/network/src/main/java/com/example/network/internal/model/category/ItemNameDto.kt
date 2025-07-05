package com.example.network.internal.model.category

import kotlinx.serialization.Serializable

@Serializable
data class ItemNameDto(
    val name: String = "",
    val slug: String = ""
)