package com.example.network.internal.model.category

import kotlinx.serialization.Serializable

@Serializable
internal data class ItemNameDto(
    val name: String = "",
    val slug: String = ""
)