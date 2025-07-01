package com.example.network.internal.model.movie

import kotlinx.serialization.Serializable

@Serializable
internal data class CommentDto(
    val id: Int,
    val movieId: Int,
    val title: String,
    val type: String,
    val review: String,
    val date: String,
    val author: String,
    val userRating: Int,
    val authorId: Int,
    val createdAt: String,
    val updatedAt: String,
    val reviewDislikes: Int,
    val reviewLikes: Int,
)