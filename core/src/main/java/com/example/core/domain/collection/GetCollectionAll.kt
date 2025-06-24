package com.example.core.domain.collection

import com.example.core.data.repositories.CollectionRepository
import com.example.network.model.image.Collection
import com.example.network.utils.Constants
import com.example.network.utils.Constants.PAGE_FIELD
import javax.inject.Inject

class GetCollectionAll @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend fun execute(page: Int = 1): Result<List<Collection>> {
        val queryParameters = listOf(
            PAGE_FIELD to page.toString(),
            Constants.SLUG_FIELD to "!hd"
        )

        return collectionRepository.getCollections(queryParameters)
    }
}