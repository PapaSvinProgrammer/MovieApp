package com.example.core.domain.comment

import com.example.core.data.repositories.CommentRepository
import com.example.network.model.movie.Comment
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.POSITIVE_VALUE
import com.example.network.utils.Constants.TYPE_FIELD
import javax.inject.Inject

class GetCommentOnlyPositive @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1
    ): Result<List<Comment>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            TYPE_FIELD to POSITIVE_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}