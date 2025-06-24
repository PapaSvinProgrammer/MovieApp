package com.example.network.model.person

import kotlinx.serialization.Serializable

@Serializable
data class Award(
    val title: String?,
    val year: Int?
)