package com.example.network.internal.model.person

import kotlinx.serialization.Serializable

@Serializable
internal data class AwardDto(
    val title: String?,
    val year: Int?
)