package com.example.core.domain.usecases

import com.example.core.domain.repositories.MovieRepository
import com.example.network.module.QueryParameters
import com.example.network.module.movie.Movie
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getById(movieId: Int): Movie? {
        return movieRepository.getMovieById(movieId)
    }

    suspend fun search(q: String, page: Int): List<Movie> {
        if (q.length < 3 || page <= 0) {
            return listOf()
        }

        return movieRepository.search(q, page)
    }

    suspend fun getByFilter(queryParameters: Map<String, String>): List<Movie> {
        return movieRepository.getMovieByFilter(queryParameters)
    }
}