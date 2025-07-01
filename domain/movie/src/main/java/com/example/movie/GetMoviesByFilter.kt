package com.example.movie

import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
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