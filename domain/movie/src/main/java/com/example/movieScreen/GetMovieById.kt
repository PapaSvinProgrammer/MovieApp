package com.example.movieScreen

import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<Int, Result<Movie>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Movie> {
        return movieRepository.getMovieById(params)
    }
}
