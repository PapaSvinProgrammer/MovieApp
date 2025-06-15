package com.example.core.data.repositories

import com.example.core.domain.repositories.CommentRepository
import com.example.network.module.movie.Comment
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): CommentRepository {
    override suspend fun getCommentsByFilter(queryParameters: List<Pair<String, String>>): List<Comment> {
        return try {
            ktorClient.getCommentsByFilter(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }
}