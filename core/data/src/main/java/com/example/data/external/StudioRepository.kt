package com.example.data.external

import com.example.model.movie.Studio

interface StudioRepository {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>>
}