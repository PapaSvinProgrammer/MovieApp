package com.example.collectionusecase

import com.example.data.external.CollectionRepository
import com.example.model.image.CollectionMovie
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.SLUG_FIELD
import com.example.utils.UseCase
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
