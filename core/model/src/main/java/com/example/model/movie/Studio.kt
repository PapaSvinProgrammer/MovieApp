package com.example.network.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class Studio(
    val id: String?,
    val subType: String?,
    val title: String?,
    val type: String?,
    val movies: List<Movie>,
    val createdAt: String?,
    val updatedAt: String?,
)