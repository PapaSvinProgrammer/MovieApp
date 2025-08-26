package com.mordva.domain.usecase.comment

import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.usecase.comment.model.CommentParams
import com.mordva.model.movie.Comment
import com.mordva.util.Constants.DATE_FIELD
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.SORT_FIELD
import com.mordva.util.Constants.SORT_TYPE
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetCommentByDate @Inject constructor(
    private val commentRepository: CommentRepository
) : UseCase<CommentParams, Result<List<Comment>>>(Dispatchers.IO) {
    override suspend fun run(params: CommentParams): Result<List<Comment>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to params.movieId.toString(),
            PAGE_FIELD to params.page.toString(),
            SORT_FIELD to DATE_FIELD,
            SORT_TYPE to params.sort.toString()
        )

        return commentRepository.getCommentsByFilter(queryParameters)
    }
}