package com.example.data.repositoriesImpl

import com.example.core.data.repositories.CollectionRepository
import com.example.network.model.image.Collection
import com.example.network.service.CollectionService
import javax.inject.Inject

internal class CollectionRepositoryImpl @Inject constructor(
    private val service: CollectionService
): CollectionRepository {
    override suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Collection>> {
        return service.getCollectionByFilter(queryParameters)
    }

    override suspend fun getCollectionBySlug(slug: String): Result<Collection> {
        return service.getCollectionBySlug(slug)
    }
}