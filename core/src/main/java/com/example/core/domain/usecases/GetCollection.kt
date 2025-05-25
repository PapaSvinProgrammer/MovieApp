package com.example.core.domain.usecases

import com.example.core.domain.repositories.CollectionRepository
import com.example.network.Constants
import com.example.network.Constants.PAGE_FIELD
import com.example.network.module.image.Collection
import javax.inject.Inject

class GetCollection @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun getCollections(page: Int = 1): List<Collection> {
        val queryParameters = mapOf(
            PAGE_FIELD to page.toString()
        )

        return collectionRepository.getCollections(queryParameters)
    }
}