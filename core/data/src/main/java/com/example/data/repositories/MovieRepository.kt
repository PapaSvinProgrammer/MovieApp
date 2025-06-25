package com.example.data.repositories

import com.example.network.model.image.Poster
import com.example.network.model.movie.Movie

interface MovieRepository {
    suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>>

    suspend fun getMovieById(movieId: Int): Result<Movie>

    suspend fun search(
        q: String,
        page: Int = 1
    ): Result<List<Movie>>

    suspend fun getImages(
        movieId: Int,
        page: Int = 1
    ): Result<List<Poster>>
}