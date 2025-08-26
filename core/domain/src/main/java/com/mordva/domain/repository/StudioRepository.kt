package com.mordva.domain.repository

import com.mordva.model.movie.Studio

interface StudioRepository {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>>
}