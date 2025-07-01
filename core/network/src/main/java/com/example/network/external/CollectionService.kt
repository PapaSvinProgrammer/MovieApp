package com.example.network.external

import com.example.model.image.Collection

interface CollectionService {
    suspend fun getCollectionByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Collection>>

    suspend fun getCollectionBySlug(slug: String): Result<Collection>
}