package com.example.core.domain.repositories

import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType

interface CategoryRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getMovieTypes(): List<MovieType>
    suspend fun getCounties(): List<Country>
}