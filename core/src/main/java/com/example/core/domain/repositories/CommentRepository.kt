package com.example.core.domain.repositories

import com.example.network.module.movie.Comment

interface CommentRepository {
    suspend fun getCommentsByFilter(queryParameters: List<Pair<String, String>>): List<Comment>
}