package com.example.homescreen.domain

import com.example.core.data.repositories.MovieRepository
import com.example.network.model.movie.Movie
import com.example.network.utils.Constants
import javax.inject.Inject

class GetMoviesByGenre @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(
        genre: String,
        page: Int = 1
    ): Result<List<Movie>> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.GENRES_NAME_FIELD to genre
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }
}