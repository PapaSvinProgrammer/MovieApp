package com.mordva.network.external

import com.mordva.model.image.MovieImageParams
import com.mordva.model.image.Poster

interface ImageService {
    suspend fun getMovieImages(movieId: Int, page: Int = 1): Result<List<Poster>>
    suspend fun getMoviesImagesByType(params: MovieImageParams): Result<List<Poster>>
}
