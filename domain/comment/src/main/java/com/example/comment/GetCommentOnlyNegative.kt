package com.example.comment

import com.example.comment.model.CommentParams
import com.example.data.external.CommentRepository
import com.example.model.movie.Comment
import com.example.utils.Constants.MOVIE_ID_FIELD
import com.example.utils.Constants.NEGATIVE_VALUE
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.TYPE_FIELD
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetCommentOnlyNegative @Inject constructor(
    private val commentRepository: CommentRepository
) : UseCase<CommentParams, Result<List<Comment>>>(Dispatchers.IO) {
    override suspend fun run(params: CommentParams): Result<List<Comment>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to params.movieId.toString(),
            PAGE_FIELD to params.page.toString(),
            TYPE_FIELD to NEGATIVE_VALUE
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}