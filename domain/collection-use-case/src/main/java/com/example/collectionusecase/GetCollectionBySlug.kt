package com.example.collectionusecase

import com.example.data.external.CollectionRepository
import com.example.model.image.CollectionMovie
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetCollectionBySlug @Inject constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<String, Result<CollectionMovie>>(Dispatchers.IO) {
    override suspend fun run(params: String): Result<CollectionMovie> {
        return collectionRepository.getCollectionBySlug(params)
    }
}