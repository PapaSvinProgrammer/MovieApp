package com.example.movieScreen

import com.example.data.external.MovieRepository
import com.example.model.image.Poster
import javax.inject.Inject

class GetMovieImages @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1
    ): Result<List<Poster>> {
        return movieRepository.getImages(movieId, page)
    }
}