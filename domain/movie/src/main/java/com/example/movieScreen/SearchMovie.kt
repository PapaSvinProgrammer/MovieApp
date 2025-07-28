package com.example.movieScreen

import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
import com.example.movieScreen.model.MovieParams
import com.example.utils.UseCase
import com.example.utils.error.ClientException
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchMovie @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<MovieParams, Result<List<Movie>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Movie>> {
        if (params.q.length < 3 || params.page <= 0) {
            return Result.failure(ClientException())
        }

        return movieRepository.search(
            q = params.q,
            page = params.page
        )
    }
}
