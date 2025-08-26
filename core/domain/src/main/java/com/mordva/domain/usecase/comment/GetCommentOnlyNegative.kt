package com.mordva.domain.usecase.comment

import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.usecase.comment.model.CommentParams
import com.mordva.model.movie.Comment
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.NEGATIVE_VALUE
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.TYPE_FIELD
import com.mordva.util.UseCase
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