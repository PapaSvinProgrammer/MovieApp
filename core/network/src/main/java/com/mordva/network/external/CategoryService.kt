package com.mordva.network.external

import com.mordva.model.category.ItemName

interface CategoryService {
    suspend fun getGenres(): Result<List<ItemName>>

    suspend fun getMovieTypes(): Result<List<ItemName>>

    suspend fun getCountries(): Result<List<ItemName>>
}