package com.example.core.data.repositories

import com.example.core.domain.repositories.StudioRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.movie.Studio
import com.example.network.service.StudiesService
import javax.inject.Inject

class StudioRepositoryImpl @Inject constructor(
    private val service: StudiesService
): StudioRepository {
    override suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Studio>, NetworkError> {
        return service.getStudies(queryParameters)
    }
}