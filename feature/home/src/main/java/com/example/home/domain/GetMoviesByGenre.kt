package com.example.home.domain

import com.example.utils.Constants
import com.example.data.external.MovieRepository
import com.example.model.movie.Movie
import com.example.movieScreen.model.MovieParams
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetMoviesByGenre @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<MovieParams, Result<List<Movie>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Movie>> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to params.page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.GENRES_NAME_FIELD to params.genre
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }
}
