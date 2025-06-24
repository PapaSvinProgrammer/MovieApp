package com.example.core.domain.collection

import com.example.core.data.repositories.CollectionRepository
import com.example.network.model.image.Collection
import com.example.network.utils.Constants.CATEGORY_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import javax.inject.Inject

class GetCollectionByCategory @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(
        category: String,
        page: Int = 1
    ): Result<List<Collection>> {
        val queryParameters = listOf(
            PAGE_FIELD to page.toString(),
            CATEGORY_FIELD to category
        )

        return collectionRepository.getCollections(queryParameters)
    }
}