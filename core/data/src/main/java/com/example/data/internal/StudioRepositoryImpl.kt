package com.example.data.internal

import com.example.data.external.StudioRepository
import com.example.model.movie.Studio
import com.example.network.external.StudiesService
import javax.inject.Inject

class StudioRepositoryImpl @Inject constructor(
    private val service: StudiesService
) : StudioRepository {
    override suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>> {
        return service.getStudies(queryParameters)
    }
}