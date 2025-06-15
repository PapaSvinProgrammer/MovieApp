package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.image.Poster
import com.example.network.module.movie.Movie

interface MovieRepository {
    suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Movie>, NetworkError>

    suspend fun getMovieById(movieId: Int): Operation<Movie, NetworkError>

    suspend fun search(
        q: String,
        page: Int = 1
    ): Operation<Docs<Movie>, NetworkError>

    suspend fun getImages(
        movieId: Int,
        page: Int = 1
    ): Operation<Docs<Poster>, NetworkError>
}