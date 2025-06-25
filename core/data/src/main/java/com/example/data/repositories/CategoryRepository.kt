package com.example.data.repositories

import com.example.network.model.category.Country
import com.example.network.model.category.Genre
import com.example.network.model.category.MovieType

interface CategoryRepository {
    suspend fun getGenres(): Result<List<Genre>>
    suspend fun getMovieTypes(): Result<List<MovieType>>
    suspend fun getCounties(): Result<List<Country>>
}