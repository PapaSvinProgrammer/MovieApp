package com.mordva.domain.usecase.comment.model

import androidx.annotation.IntRange

data class CommentParams(
    val movieId: Int,
    val page: Int = 1,
    @IntRange(from = -1, to = 1) val sort: Int = 1
)