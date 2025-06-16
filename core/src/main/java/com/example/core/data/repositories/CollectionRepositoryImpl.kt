package com.example.core.data.repositories

import com.example.core.domain.repositories.CollectionRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Collection
import com.example.network.module.image.Docs
import com.example.network.service.CollectionService
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val service: CollectionService
): CollectionRepository {
    override suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Collection>, NetworkError> {
        return service.getCollectionByFilter(queryParameters)
    }

    override suspend fun getCollectionBySlug(slug: String): Operation<Collection, NetworkError> {
        return service.getCollectionBySlug(slug)
    }
}