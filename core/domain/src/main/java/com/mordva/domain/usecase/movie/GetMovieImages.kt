package com.mordva.domain.usecase.movie

import com.mordva.domain.repository.ImageRepository
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.model.image.Poster
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieImages @Inject constructor(
    private val imageRepository: ImageRepository
) : UseCase<MovieParams, Result<List<Poster>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Poster>> {
        return imageRepository.getMovieImages(params.movieId, params.page)
    }
}
