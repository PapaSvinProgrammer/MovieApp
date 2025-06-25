package com.example.collection

import com.example.core.data.repositories.CollectionRepository
import com.example.network.model.image.Collection
import javax.inject.Inject

class GetCollectionBySlug @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(slug: String): Result<Collection> {
        return collectionRepository.getCollectionBySlug(slug)
    }
}