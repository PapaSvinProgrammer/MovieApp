package com.mordva.network.internal.service

import com.mordva.model.image.CollectionMovie
import com.mordva.network.internal.model.image.CollectionDto
import com.mordva.network.external.CollectionService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.internal.mapper.toDomain
import com.mordva.network.internal.model.image.Docs
import com.mordva.util.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class CollectionServiceImpl @Inject constructor(
    private val client: HttpClient
) : CollectionService {
    override suspend fun getCollectionByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionMovie>> {
        return safeCall<Docs<CollectionDto>> {
            client.get("v1.4/list") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }

    override suspend fun getCollectionBySlug(slug: String): Result<CollectionMovie> {
        return safeCall<CollectionDto> {
            client.get("v1.4/list/$slug")
        }.map { it.toDomain() }
    }
}