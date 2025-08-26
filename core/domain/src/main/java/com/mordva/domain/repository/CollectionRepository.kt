package com.mordva.domain.repository

import com.mordva.model.image.CollectionMovie

interface CollectionRepository {
    suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Result<List<CollectionMovie>>

    suspend fun getCollectionBySlug(slug: String): Result<CollectionMovie>
}