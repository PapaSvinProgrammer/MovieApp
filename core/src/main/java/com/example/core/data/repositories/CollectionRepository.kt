package com.example.core.data.repositories

import com.example.network.model.image.Collection

interface CollectionRepository {
    suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Collection>>

    suspend fun getCollectionBySlug(slug: String): Result<Collection>
}