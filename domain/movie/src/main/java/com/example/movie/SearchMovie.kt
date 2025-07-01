package com.example.movie

import com.example.common.ClientException
import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
import javax.inject.Inject

class SearchMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun search(
        q: String,
        page: Int = 1
    ): Result<List<Movie>> {
        if (q.length < 3 || page <= 0) {
            return Result.failure(ClientException())
        }

        return movieRepository.search(
            q = q,
            page = page
        )
    }
}