package com.example.comment

import com.example.data.external.CommentRepository
import com.example.model.movie.Comment
import com.example.utils.Constants.MOVIE_ID_FIELD
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.POSITIVE_VALUE
import com.example.utils.Constants.TYPE_FIELD
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