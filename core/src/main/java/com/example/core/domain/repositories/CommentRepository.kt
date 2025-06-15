package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.movie.Comment

interface CommentRepository {
    suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Comment>, NetworkError>
}