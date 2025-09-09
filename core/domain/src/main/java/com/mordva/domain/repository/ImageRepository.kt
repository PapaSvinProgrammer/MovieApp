package com.mordva.domain.repository

import com.mordva.model.image.MovieImageParams
import com.mordva.model.image.Poster

interface ImageRepository {
    suspend fun getMovieImages(movieId: Int, page: Int = 1): Result<List<Poster>>
    suspend fun getMoviesImagesByType(params: MovieImageParams): Result<List<Poster>>
}