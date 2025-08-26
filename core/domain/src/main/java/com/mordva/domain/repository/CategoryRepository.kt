package com.mordva.domain.repository

import com.mordva.model.category.ItemName

interface CategoryRepository {
    suspend fun getGenres(): Result<List<ItemName>>

    suspend fun getMovieTypes(): Result<List<ItemName>>

    suspend fun getCounties(): Result<List<ItemName>>
}