package com.example.core.domain.usecases

import com.example.core.domain.common.Constants.DATE_FIELD
import com.example.core.domain.common.Constants.TYPE_FIELD
import com.example.core.domain.common.Constants.MOVIE_ID_FIELD
import com.example.core.domain.common.Constants.NEGATIVE_VALUE
import com.example.core.domain.common.Constants.NEUTRAL_VALUE
import com.example.core.domain.common.Constants.PAGE_FIELD
import com.example.core.domain.common.Constants.POSITIVE_VALUE
import com.example.core.domain.common.Constants.SORT_ASC
import com.example.core.domain.common.Constants.SORT_DESC
import com.example.core.domain.common.Constants.SORT_FIELD
import com.example.core.domain.common.Constants.SORT_TYPE
import com.example.core.domain.repositories.CommentRepository
import com.example.network.module.movie.Comment
import javax.inject.Inject

class GetComment @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun getByDateAsc(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to DATE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }

    suspend fun getByDateDesc(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to DATE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }

    suspend fun getByTypePositive(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TYPE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }

    suspend fun getByTypeNegative(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TYPE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }

    suspend fun getOnlyPositive(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            TYPE_FIELD to POSITIVE_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }

    suspend fun getOnlyNegative(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            TYPE_FIELD to NEGATIVE_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }

    suspend fun getOnlyNeutral(movieId: Int, page: Int): List<Comment> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            TYPE_FIELD to NEUTRAL_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}