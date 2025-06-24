package com.example.core.data.repositories

import com.example.network.model.movie.Studio

interface StudioRepository {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>>
}