package com.mordva.data

import com.mordva.domain.repository.CategoryRepository
import com.mordva.model.category.ItemName
import com.mordva.network.external.CategoryService
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
) : CategoryRepository {
    override suspend fun getGenres(): Result<List<ItemName>> {
        return service.getGenres()
    }

    override suspend fun getMovieTypes(): Result<List<ItemName>> {
        return service.getMovieTypes()
    }

    override suspend fun getCounties(): Result<List<ItemName>> {
        return service.getCountries()
    }
}