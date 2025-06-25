package com.example.movie

import com.example.core.data.repositories.MovieRepository
import com.example.network.model.image.Poster
import javax.inject.Inject

class GetMoviesImages @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1
    ): Result<List<Poster>> {
        return movieRepository.getImages(movieId, page)
    }
}