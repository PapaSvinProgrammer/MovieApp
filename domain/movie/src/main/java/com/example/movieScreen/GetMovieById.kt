package com.example.movieScreen

import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(movieId: Int): Result<Movie> {
        return movieRepository.getMovieById(movieId)
    }
}