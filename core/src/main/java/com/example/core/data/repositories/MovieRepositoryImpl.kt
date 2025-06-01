package com.example.core.data.repositories

import com.example.core.domain.repositories.MovieRepository
import com.example.network.KtorClient
import com.example.network.module.movie.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): MovieRepository {
    override suspend fun getMovieByFilter(queryParameters: Map<String, String>): List<Movie> {
        return try {
            ktorClient.getMoviesByFilter(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getMovieById(movieId: Int): Movie? {
        return try {
            ktorClient.getMovieById(movieId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun search(q: String, page: Int): List<Movie> {
        return try {
            ktorClient.searchMovieByName(
                page = page,
                q = q
            )
        } catch (e: Exception) {
            listOf()
        }
    }
}