package com.example.data.internal

import com.example.data.external.CollectionRepository
import com.example.model.image.Collection
import com.example.network.external.CollectionService
import javax.inject.Inject

internal class CollectionRepositoryImpl @Inject constructor(
    private val service: CollectionService
) : CollectionRepository {
    override suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Collection>> {
        return service.getCollectionByFilter(queryParameters)
    }

    override suspend fun getCollectionBySlug(slug: String): Result<Collection> {
        return service.getCollectionBySlug(slug)
    }
}