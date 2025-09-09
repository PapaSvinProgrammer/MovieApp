package com.mordva.domain.repository

import com.mordva.model.movie.Movie

interface MovieRepository {
    suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>>

    suspend fun getMovieById(movieId: Int): Result<Movie>

    suspend fun search(
        q: String,
        page: Int = 1
    ): Result<List<Movie>>
}