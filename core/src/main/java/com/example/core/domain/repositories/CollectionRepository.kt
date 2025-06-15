package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Collection
import com.example.network.module.image.Docs

interface CollectionRepository {
    suspend fun getCollections(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Collection>, NetworkError>
}