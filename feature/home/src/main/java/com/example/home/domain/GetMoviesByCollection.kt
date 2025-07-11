package com.example.home.domain


import com.example.common.Constants
import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
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