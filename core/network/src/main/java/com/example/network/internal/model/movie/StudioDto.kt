package com.example.network.internal.model.movie

import kotlinx.serialization.Serializable

@Serializable
internal data class StudioDto(
    val id: String?,
    val subType: String?,
    val title: String?,
    val type: String?,
    val movieDto: List<MovieDto>,
    val createdAt: String?,
    val updatedAt: String?
)