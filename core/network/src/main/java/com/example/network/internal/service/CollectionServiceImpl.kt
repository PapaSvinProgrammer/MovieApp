package com.example.network.internal.service

import com.example.common.Constants.LIMIT_FIELD
import com.example.model.image.CollectionMovie
import com.example.network.internal.model.image.CollectionDto
import com.example.network.external.CollectionService
import com.example.network.internal.core.LIMIT_API_COUNT
import com.example.network.internal.core.safeCall
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.model.image.Docs
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class CollectionServiceImpl @Inject constructor(
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