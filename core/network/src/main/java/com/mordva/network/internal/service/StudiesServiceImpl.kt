package com.mordva.network.internal.service

import com.mordva.model.movie.Studio
import com.mordva.network.internal.model.movie.StudioDto
import com.mordva.network.external.StudiesService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.internal.mapper.toDomain
import com.mordva.network.internal.model.image.Docs
import com.mordva.util.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

internal class StudiesServiceImpl @Inject constructor(
    private val client: HttpClient
) : StudiesService {
    override suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>> {
        return safeCall<Docs<StudioDto>> {
            client.get("v1.4/studio") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }
}