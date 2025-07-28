package com.example.collectionusecase

import com.example.collectionusecase.model.CollectionParams
import com.example.data.external.CollectionRepository
import com.example.model.image.CollectionMovie
import com.example.utils.Constants.CATEGORY_FIELD
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetCollectionByCategory @Inject constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<CollectionParams, Result<List<CollectionMovie>>>(Dispatchers.IO) {
    override suspend fun run(params: CollectionParams): Result<List<CollectionMovie>> {
        val queryParameters = listOf(
            PAGE_FIELD to params.page.toString(),
            CATEGORY_FIELD to params.category
        )

        return collectionRepository.getCollections(queryParameters)
    }
}
