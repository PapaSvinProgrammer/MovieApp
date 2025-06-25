package com.example.movie

import com.example.core.data.repositories.MovieRepository
import com.example.network.model.movie.Movie
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(movieId: Int): Result<Movie> {
        return movieRepository.getMovieById(movieId)
    }
}