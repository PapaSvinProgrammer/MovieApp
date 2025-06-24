package com.example.core.data.repositoriesImpl

import com.example.core.data.repositories.CommentRepository
import com.example.network.model.movie.Comment
import com.example.network.service.CommentService
import javax.inject.Inject

internal class CommentRepositoryImpl @Inject constructor(
    private val service: CommentService
) : CommentRepository {
    override suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>> {
        return service.getCommentsByFilter(queryParameters)
    }
}