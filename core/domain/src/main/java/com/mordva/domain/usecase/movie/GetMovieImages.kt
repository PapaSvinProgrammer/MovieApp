package com.mordva.domain.usecase.movie

import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.model.image.Poster
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieImages @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<MovieParams, Result<List<Poster>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Poster>> {
        return movieRepository.getImages(params.movieId, params.page)
    }
}
