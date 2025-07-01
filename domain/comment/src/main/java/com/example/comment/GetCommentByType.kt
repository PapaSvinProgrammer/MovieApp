package com.example.comment

import androidx.annotation.IntRange
import com.example.common.Constants.MOVIE_ID_FIELD
import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.SORT_FIELD
import com.example.common.Constants.SORT_TYPE
import com.example.common.Constants.TYPE_FIELD
import com.example.data.external.CommentRepository
import com.example.model.movie.Comment
import javax.inject.Inject

class GetCommentByType @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1,
        @IntRange(from = -1, to = 1) sort: Int = 1
    ): Result<List<Comment>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TYPE_FIELD,
            SORT_TYPE to sort.toString()
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}