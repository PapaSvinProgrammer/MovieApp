package com.example.network.external

import com.example.model.image.Poster
import com.example.model.movie.Movie

interface MovieService {
    suspend fun getMovieById(movieId: Int): Result<Movie>

    suspend fun searchMovieByName(page: Int, q: String): Result<List<Movie>>

    suspend fun getMoviesByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>>

    suspend fun getMovieImages(
        movieId: Int,
        page: Int = 1
    ): Result<List<Poster>>
}