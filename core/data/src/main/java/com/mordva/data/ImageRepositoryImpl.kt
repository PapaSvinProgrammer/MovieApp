package com.mordva.data

import com.mordva.domain.repository.ImageRepository
import com.mordva.model.image.MovieImageParams
import com.mordva.model.image.Poster
import com.mordva.network.external.ImageService
import javax.inject.Inject

internal class ImageRepositoryImpl @Inject constructor(
    private val service: ImageService
) : ImageRepository {
    override suspend fun getMovieImages(movieId: Int, page: Int): Result<List<Poster>> {
        return service.getMovieImages(movieId, page)
    }

    override suspend fun getMoviesImagesByType(params: MovieImageParams): Result<List<Poster>> {
        return service.getMoviesImagesByType(params)
    }
}