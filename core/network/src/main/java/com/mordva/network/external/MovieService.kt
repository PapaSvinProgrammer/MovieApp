package com.mordva.network.external

import com.mordva.model.movie.Movie

interface MovieService {
    suspend fun getMovieById(movieId: Int): Result<Movie>

    suspend fun searchMovieByName(page: Int, q: String): Result<List<Movie>>

    suspend fun getMoviesByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>>
}