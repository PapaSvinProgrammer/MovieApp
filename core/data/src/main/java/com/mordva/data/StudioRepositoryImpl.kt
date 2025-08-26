package com.mordva.data

import com.mordva.domain.repository.StudioRepository
import com.mordva.model.movie.Studio
import com.mordva.network.external.StudiesService
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