package com.example.network.external

import com.example.model.movie.Comment

interface CommentService {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>>
}