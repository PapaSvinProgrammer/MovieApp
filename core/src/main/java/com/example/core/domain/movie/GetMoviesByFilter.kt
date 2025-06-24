package com.example.core.domain.movie

import com.example.core.data.repositories.MovieRepository
import com.example.network.model.movie.Movie
import javax.inject.Inject

class GetMoviesByFilter @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>> {
        return movieRepository.getMovieByFilter(queryParameters)
    }
}