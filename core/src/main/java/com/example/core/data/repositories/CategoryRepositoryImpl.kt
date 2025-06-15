package com.example.core.data.repositories

import com.example.core.domain.repositories.CategoryRepository
import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): CategoryRepository {
    override suspend fun getGenres(): List<Genre> {
        return ktorClient.getGenres()
    }

    override suspend fun getMovieTypes(): List<MovieType> {
        return ktorClient.getMovieTypes()
    }

    override suspend fun getCounties(): List<Country> {
        return ktorClient.getCountries()
    }
}