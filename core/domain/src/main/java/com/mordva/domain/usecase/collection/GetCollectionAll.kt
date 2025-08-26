package com.mordva.domain.usecase.collection

import com.mordva.domain.repository.CollectionRepository
import com.mordva.model.image.CollectionMovie
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.SLUG_FIELD
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetCollectionAll @Inject constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<Int, Result<List<CollectionMovie>>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<List<CollectionMovie>> {
        val queryParameters = listOf(
            PAGE_FIELD to params.toString(),
            SLUG_FIELD to "!hd"
        )

        return collectionRepository.getCollections(queryParameters)
    }
}
