package com.example.data.internal

import com.example.data.external.MovieRepository
import com.example.model.image.Poster
import com.example.model.movie.Movie
import com.example.network.external.MovieService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
) : MovieRepository {
    override suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>> {
        return  service.getMoviesByFilter(queryParameters)
    }

    override suspend fun getMovieById(movieId: Int): Result<Movie> {
        return service.getMovieById(movieId)
    }

    override suspend fun search(
        q: String,
        page: Int
    ): Result<List<Movie>> {
        return service.searchMovieByName(
            page = page,
            q = q
        )
    }

    override suspend fun getImages(
        movieId: Int,
        page: Int
    ): Result<List<Poster>> {
        return service.getMovieImages(movieId, page)
    }
}