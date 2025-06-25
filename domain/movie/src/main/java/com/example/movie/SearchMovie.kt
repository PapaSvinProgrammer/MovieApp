package com.example.movie

import com.example.core.data.repositories.MovieRepository
import com.example.network.core.ClientException
import com.example.network.model.movie.Movie
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