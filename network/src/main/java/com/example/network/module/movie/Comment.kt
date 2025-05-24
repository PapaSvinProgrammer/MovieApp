package com.example.network.module.movie

data class Comment(
    val id: Int,
    val movieId: Int,
    val title: String,
    val type: String,
    val review: String,
    val date: String,
    val author: String,
    val userRating: Int,
    val authorId: Long,
    val createdAt: String,
    val updatedAt: String,
    val reviewDislikes: Int,
    val reviewLikes: Int,
)