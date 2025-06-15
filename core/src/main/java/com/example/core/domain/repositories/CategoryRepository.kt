package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType

interface CategoryRepository {
    suspend fun getGenres(): Operation<List<Genre>, NetworkError>
    suspend fun getMovieTypes(): Operation<List<MovieType>, NetworkError>
    suspend fun getCounties(): Operation<List<Country>, NetworkError>
}