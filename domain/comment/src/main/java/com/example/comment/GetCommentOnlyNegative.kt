package com.example.comment

import com.example.common.Constants.MOVIE_ID_FIELD
import com.example.common.Constants.NEGATIVE_VALUE
import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.TYPE_FIELD
import com.example.data.external.CommentRepository
import com.example.model.movie.Comment
import javax.inject.Inject

class GetCommentOnlyNegative @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1
    ): Result<List<Comment>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            TYPE_FIELD to NEGATIVE_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}