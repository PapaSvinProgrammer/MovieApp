package com.example.collectionusecase

import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.SLUG_FIELD
import com.example.data.external.CollectionRepository
import com.example.model.image.CollectionMovie
import javax.inject.Inject

class GetCollectionAll @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(page: Int = 1): Result<List<CollectionMovie>> {
        val queryParameters = listOf(
            PAGE_FIELD to page.toString(),
            SLUG_FIELD to "!hd"
        )

        return collectionRepository.getCollections(queryParameters)
    }
}