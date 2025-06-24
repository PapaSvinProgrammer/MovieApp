package com.example.core.domain.movie

import com.example.core.data.repositories.MovieRepository
import com.example.network.model.movie.Movie
import com.example.network.utils.Constants
import javax.inject.Inject

class GetMoviesByCollection @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(
        name: String,
        page: Int = 1
    ): Result<List<Movie>> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.LISTS_FIELD to name
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }
}