package com.mordva.movieScreen.domain

import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.movieScreen.domain.model.RatedMovieParams
import com.mordva.movieScreen.utils.toRatedMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class AddRatedMovie @Inject constructor(
    private val repository: RatedMovieRepository
) : UseCase<RatedMovieParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: RatedMovieParams) {
        val ratedMovie = params.movie.toRatedMovie(params.rating)
        repository.add(ratedMovie)
    }
}
