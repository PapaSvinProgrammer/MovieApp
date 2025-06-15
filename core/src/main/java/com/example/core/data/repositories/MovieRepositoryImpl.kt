package com.example.core.data.repositories

import com.example.core.domain.repositories.MovieRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.image.Poster
import com.example.network.module.movie.Movie
import com.example.network.service.MovieService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
): MovieRepository {
    override suspend fun getMovieByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Movie>, NetworkError> {
        return  service.getMoviesByFilter(queryParameters)
    }

    override suspend fun getMovieById(movieId: Int): Operation<Movie, NetworkError> {
        return service.getMovieById(movieId)
    }

    override suspend fun search(
        q: String,
        page: Int
    ): Operation<Docs<Movie>, NetworkError> {
        return service.searchMovieByName(
            page = page,
            q = q
        )
    }

    override suspend fun getImages(
        movieId: Int,
        page: Int
    ): Operation<Docs<Poster>, NetworkError> {
        return service.getMovieImages(movieId, page)
    }
}