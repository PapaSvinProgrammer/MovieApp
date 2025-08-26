package com.mordva.domain.usecase.collection

import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.usecase.collection.model.CollectionParams
import com.mordva.model.image.CollectionMovie
import com.mordva.util.Constants.CATEGORY_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.UseCase
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
