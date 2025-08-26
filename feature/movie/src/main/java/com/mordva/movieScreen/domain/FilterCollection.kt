package com.mordva.movieScreen.domain

import com.mordva.model.image.CollectionMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class FilterCollection @Inject constructor(
) : UseCase<List<CollectionMovie>, List<CollectionMovie>>(Dispatchers.Default) {
    override suspend fun run(params: List<CollectionMovie>): List<CollectionMovie> {
        return params.filter { it.slug != "hd" }
    }
}
