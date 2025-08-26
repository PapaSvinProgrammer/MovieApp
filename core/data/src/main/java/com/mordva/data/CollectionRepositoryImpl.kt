package com.mordva.data

import com.mordva.domain.repository.CollectionRepository
import com.mordva.model.image.CollectionMovie
import com.mordva.network.external.CollectionService
import javax.inject.Inject

internal class CollectionRepositoryImpl @Inject constructor(
    private val service: CollectionService
) : CollectionRepository {
    override suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionMovie>> {
        return service.getCollectionByFilter(queryParameters)
    }

    override suspend fun getCollectionBySlug(slug: String): Result<CollectionMovie> {
        return service.getCollectionBySlug(slug)
    }
}