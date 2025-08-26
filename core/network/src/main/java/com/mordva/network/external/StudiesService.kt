package com.mordva.network.external

import com.mordva.model.movie.Studio

interface StudiesService {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>>
}