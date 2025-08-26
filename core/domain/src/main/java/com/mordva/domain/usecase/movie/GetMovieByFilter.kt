package com.mordva.domain.usecase.movie

import com.mordva.domain.repository.MovieRepository
import com.mordva.model.movie.Movie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieByFilter @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<List<Pair<String, String>>, Result<List<Movie>>>(Dispatchers.IO) {
    override suspend fun run(params: List<Pair<String, String>>): Result<List<Movie>> {
        return movieRepository.getMovieByFilter(params)
    }
}
