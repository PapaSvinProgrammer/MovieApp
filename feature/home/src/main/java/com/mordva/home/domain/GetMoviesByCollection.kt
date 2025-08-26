package com.mordva.home.domain

import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.util.Constants
import com.mordva.model.movie.Movie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetMoviesByCollection @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<MovieParams, Result<List<Movie>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Movie>> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to params.page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.LISTS_FIELD to params.name
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }
}