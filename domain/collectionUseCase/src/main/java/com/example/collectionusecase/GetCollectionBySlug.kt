package com.example.collectionusecase

import com.example.data.external.CollectionRepository
import com.example.model.image.CollectionMovie
import javax.inject.Inject

class GetCollectionBySlug @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(slug: String): Result<CollectionMovie> {
        return collectionRepository.getCollectionBySlug(slug)
    }
}