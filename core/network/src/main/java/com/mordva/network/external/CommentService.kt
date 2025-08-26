package com.mordva.network.external

import com.mordva.model.movie.Comment

interface CommentService {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>>
}