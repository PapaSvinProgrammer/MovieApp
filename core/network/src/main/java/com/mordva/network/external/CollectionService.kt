package com.mordva.network.external

import com.mordva.model.image.CollectionMovie

interface CollectionService {
    suspend fun getCollectionByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionMovie>>

    suspend fun getCollectionBySlug(slug: String): Result<CollectionMovie>
}