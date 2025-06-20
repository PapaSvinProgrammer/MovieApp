package com.example.core.domain.usecases

import com.example.core.domain.repositories.MovieRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.image.Poster
import com.example.network.utils.Constants
import com.example.network.module.movie.Movie
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getById(movieId: Int): Operation<Movie, NetworkError> {
        return movieRepository.getMovieById(movieId)
    }

    suspend fun search(
        q: String,
        page: Int = 1
    ): Operation<Docs<Movie>, NetworkError> {
        if (q.length < 3 || page <= 0) {
            return Operation.Error(NetworkError.CLIENT_ERROR)
        }

        return movieRepository.search(q, page)
    }

    suspend fun getMoviesByGenre(
        genre: String,
        page: Int = 1
    ): Operation<Docs<Movie>, NetworkError> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.GENRES_NAME_FIELD to genre
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getMoviesByCollection(
        name: String,
        page: Int = 1
    ): Operation<Docs<Movie>, NetworkError> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.LISTS_FIELD to name
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getMoviesByCompany(
        name: String,
        page: Int = 1
    ): Operation<Docs<Movie>, NetworkError> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.NETWORK_ITEMS_NAME to name
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Movie>, NetworkError> {
        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getImages(
        movieId: Int,
        page: Int = 1
    ): Operation<Docs<Poster>, NetworkError> {
        return movieRepository.getImages(movieId, page)
    }
}