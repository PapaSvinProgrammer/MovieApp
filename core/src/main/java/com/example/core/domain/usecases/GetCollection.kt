package com.example.core.domain.usecases

import com.example.core.domain.repositories.CollectionRepository
import com.example.network.utils.Constants
import com.example.network.utils.Constants.CATEGORY_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.module.image.Collection
import javax.inject.Inject

class GetCollection @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun getAll(page: Int = 1): List<Collection> {
        val queryParameters = mapOf(
            PAGE_FIELD to page.toString(),
            Constants.SLUG_FIELD to "!hd"
        )

        return collectionRepository.getCollections(queryParameters)
    }

    suspend fun getByCategory(category: String, page: Int = 1): List<Collection> {
        val queryParameters = mapOf(
            PAGE_FIELD to page.toString(),
            CATEGORY_FIELD to category
        )

        return collectionRepository.getCollections(queryParameters)
    }
}