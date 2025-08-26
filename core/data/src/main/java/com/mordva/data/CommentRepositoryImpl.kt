package com.mordva.data

import com.mordva.domain.repository.CommentRepository
import com.mordva.model.movie.Comment
import com.mordva.network.external.CommentService
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