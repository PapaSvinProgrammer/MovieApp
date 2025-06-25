package com.example.comment

import androidx.annotation.IntRange
import com.example.core.data.repositories.CommentRepository
import com.example.network.model.movie.Comment
import com.example.network.utils.Constants.DATE_FIELD
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import javax.inject.Inject

class GetCommentByDate @Inject constructor(
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
            SORT_FIELD to DATE_FIELD,
            SORT_TYPE to sort.toString()
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}