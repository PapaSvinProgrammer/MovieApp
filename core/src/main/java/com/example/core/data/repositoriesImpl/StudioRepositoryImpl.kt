package com.example.core.data.repositoriesImpl

import com.example.core.data.repositories.StudioRepository
import com.example.network.model.movie.Studio
import com.example.network.service.StudiesService
import javax.inject.Inject

internal class StudioRepositoryImpl @Inject constructor(
    private val service: StudiesService
) : StudioRepository {
    override suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>> {
        return service.getStudies(queryParameters)
    }
}