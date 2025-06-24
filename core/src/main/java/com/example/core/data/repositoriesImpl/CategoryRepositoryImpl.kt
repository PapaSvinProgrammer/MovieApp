package com.example.core.data.repositoriesImpl

import com.example.core.data.repositories.CategoryRepository
import com.example.network.model.category.Country
import com.example.network.model.category.Genre
import com.example.network.model.category.MovieType
import com.example.network.service.CategoryService
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getGenres(): Result<List<Genre>> {
        return service.getGenres()
    }

    override suspend fun getMovieTypes(): Result<List<MovieType>> {
        return service.getMovieTypes()
    }

    override suspend fun getCounties(): Result<List<Country>> {
        return service.getCountries()
    }
}