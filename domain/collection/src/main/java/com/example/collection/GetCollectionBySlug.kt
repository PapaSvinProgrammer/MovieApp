package com.example.collection

import com.example.data.external.CollectionRepository
import com.example.model.image.Collection
import javax.inject.Inject

class GetCollectionBySlug @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(slug: String): Result<Collection> {
        return collectionRepository.getCollectionBySlug(slug)
    }
}