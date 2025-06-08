package com.example.core.domain.usecases

import com.example.core.domain.repositories.MovieRepository
import com.example.network.utils.Constants
import com.example.network.module.movie.Movie
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getById(movieId: Int): Movie? {
        return movieRepository.getMovieById(movieId)
    }

    suspend fun search(q: String, page: Int = 1): List<Movie> {
        if (q.length < 2 || page <= 0) {
            return listOf()
        }

        return movieRepository.search(q, page)
    }

    suspend fun getMoviesByGenre(genre: String, page: Int = 1): List<Movie> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.GENRES_NAME_FIELD to genre
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getMoviesByCollection(name: String, page: Int = 1): List<Movie> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.LISTS_FIELD to name
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getMoviesByCompany(name: String, page: Int = 1): List<Movie> {
        val queryParameters = listOf(
            Constants.PAGE_FIELD to page.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.NETWORK_ITEMS_NAME to name
        )

        return movieRepository.getMovieByFilter(queryParameters)
    }

    suspend fun getByFilter(queryParameters: List<Pair<String, String>>): List<Movie> {
        return movieRepository.getMovieByFilter(queryParameters)
    }
}