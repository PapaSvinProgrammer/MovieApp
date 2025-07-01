package com.example.network.external

import com.example.model.category.ItemName

interface CategoryService {
    suspend fun getGenres(): Result<List<ItemName>>

    suspend fun getMovieTypes(): Result<List<ItemName>>

    suspend fun getCountries(): Result<List<ItemName>>
}