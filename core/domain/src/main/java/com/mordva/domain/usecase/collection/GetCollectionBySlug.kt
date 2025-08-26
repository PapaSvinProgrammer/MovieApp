package com.mordva.domain.usecase.collection

import com.mordva.domain.repository.CollectionRepository
import com.mordva.model.image.CollectionMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetCollectionBySlug @Inject constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<String, Result<CollectionMovie>>(Dispatchers.IO) {
    override suspend fun run(params: String): Result<CollectionMovie> {
        return collectionRepository.getCollectionBySlug(params)
    }
}