package com.example.network.external

import com.example.model.movie.Studio

interface StudiesService {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>>
}