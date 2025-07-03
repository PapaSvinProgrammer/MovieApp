package com.example.data.external

import com.example.model.image.CollectionMovie

interface CollectionRepository {
    suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionMovie>>

    suspend fun getCollectionBySlug(slug: String): Result<CollectionMovie>
}