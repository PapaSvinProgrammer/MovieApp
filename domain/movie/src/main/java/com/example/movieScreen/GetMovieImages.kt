package com.example.movieScreen

import com.example.data.external.MovieRepository
import com.example.model.image.Poster
import com.example.movieScreen.model.MovieParams
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieImages @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<MovieParams, Result<List<Poster>>>(Dispatchers.IO) {
    override suspend fun run(params: MovieParams): Result<List<Poster>> {
        return movieRepository.getImages(params.movieId, params.page)
    }
}
