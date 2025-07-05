package com.example.data.external

import com.example.model.movie.Comment

interface CommentRepository {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>>
}