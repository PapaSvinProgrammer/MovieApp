package com.example.movieScreen

import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieByFilter @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<List<Pair<String, String>>, Result<List<Movie>>>(Dispatchers.IO) {
    override suspend fun run(params: List<Pair<String, String>>): Result<List<Movie>> {
        return movieRepository.getMovieByFilter(params)
    }
}
