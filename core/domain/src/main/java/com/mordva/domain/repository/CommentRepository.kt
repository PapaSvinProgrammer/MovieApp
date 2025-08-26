package com.mordva.domain.repository

import com.mordva.model.movie.Comment

interface CommentRepository {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>>
}