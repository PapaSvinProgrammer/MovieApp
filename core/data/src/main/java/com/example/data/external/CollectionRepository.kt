package com.example.data.external

import com.example.model.image.Collection

interface CollectionRepository {
    suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Collection>>

    suspend fun getCollectionBySlug(slug: String): Result<Collection>
}