package com.mordva.images_list.domain

import com.mordva.domain.repository.ImageRepository
import com.mordva.images_list.domain.model.ImagesParams
import com.mordva.model.image.ImageType
import com.mordva.model.image.MovieImageParams
import com.mordva.model.image.Poster
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetMovieImages @Inject constructor(
    private val imageRepository: ImageRepository
) : UseCase<ImagesParams, Result<List<Poster>>>(Dispatchers.IO) {
    override suspend fun run(params: ImagesParams): Result<List<Poster>> {
        if (ImageType.ALL in params.types) {
            return getWithTypes(params)
        }

        return getWithoutTypes(params)
    }

    private suspend fun getWithoutTypes(params: ImagesParams): Result<List<Poster>> {
        return imageRepository.getMovieImages(params.movieId, params.page)
    }

    private suspend fun getWithTypes(params: ImagesParams): Result<List<Poster>> {
        val movieImageParams = MovieImageParams(
            movieId = params.movieId,
            page = params.page,
            types = params.types
        )

        return imageRepository.getMoviesImagesByType(movieImageParams)
    }
}