package com.example.core.domain.repositories

import com.example.network.module.image.Collection

interface CollectionRepository {
    suspend fun getCollections(queryParameters: List<Pair<String, String>>): List<Collection>
}