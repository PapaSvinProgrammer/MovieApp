package com.example.network.module.person

import kotlinx.serialization.Serializable

@Serializable
data class Award(
    val title: String?,
    val year: Int?
)