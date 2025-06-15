package com.example.core.data.repositories

import com.example.core.domain.repositories.CommentRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.movie.Comment
import com.example.network.service.CommentService
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val service: CommentService
): CommentRepository {
    override suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Comment>, NetworkError> {
        return service.getCommentsByFilter(queryParameters)
    }
}