package com.example.data.internal

import com.example.data.external.CommentRepository
import com.example.model.movie.Comment
import com.example.network.external.CommentService
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val service: CommentService
) : CommentRepository {
    override suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>> {
        return service.getCommentsByFilter(queryParameters)
    }
}