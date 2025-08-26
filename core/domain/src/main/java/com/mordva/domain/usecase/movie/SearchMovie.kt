package com.mordva.domain.usecase.movie

import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.model.movie.Movie
import com.mordva.util.UseCase
import com.mordva.util.error.ClientException
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
