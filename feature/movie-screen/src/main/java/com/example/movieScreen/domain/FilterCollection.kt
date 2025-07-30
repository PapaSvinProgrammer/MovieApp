package com.example.movieScreen.domain

import com.example.model.image.CollectionMovie
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class FilterCollection @Inject constructor(
) : UseCase<List<CollectionMovie>, List<CollectionMovie>>(Dispatchers.Default) {
    override suspend fun run(params: List<CollectionMovie>): List<CollectionMovie> {
        return params.filter { it.slug != "hd" }
    }
}
