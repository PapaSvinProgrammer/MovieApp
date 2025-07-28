package com.example.collectionusecase

import com.example.data.external.CollectionRepository
import com.example.model.image.CollectionMovie
import com.example.utils.Constants.CATEGORY_FIELD
import com.example.utils.Constants.PAGE_FIELD
import javax.inject.Inject

class GetCollectionByCategory @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(
        category: String,
        page: Int = 1
    ): Result<List<CollectionMovie>> {
        val queryParameters = listOf(
            PAGE_FIELD to page.toString(),
            CATEGORY_FIELD to category
        )

        return collectionRepository.getCollections(queryParameters)
    }
}