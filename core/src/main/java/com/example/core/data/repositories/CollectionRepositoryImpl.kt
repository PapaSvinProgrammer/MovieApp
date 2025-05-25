package com.example.core.data.repositories

import com.example.core.domain.repositories.CollectionRepository
import com.example.network.KtorClient
import com.example.network.module.image.Collection
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): CollectionRepository {
    override suspend fun getCollections(queryParameters: Map<String, String>): List<Collection> {
        return try {
            ktorClient.getCollectionByFilter(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }
}