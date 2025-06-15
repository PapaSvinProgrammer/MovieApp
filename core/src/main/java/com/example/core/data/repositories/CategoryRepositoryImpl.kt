package com.example.core.data.repositories

import com.example.core.domain.repositories.CategoryRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType
import com.example.network.service.CategoryService
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getGenres(): Operation<List<Genre>, NetworkError> {
        return service.getGenres()
    }

    override suspend fun getMovieTypes(): Operation<List<MovieType>, NetworkError> {
        return service.getMovieTypes()
    }

    override suspend fun getCounties(): Operation<List<Country>, NetworkError> {
        return service.getCountries()
    }
}