package com.example.data.repositories

import com.example.network.model.movie.Comment

interface CommentRepository {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>>
}