package com.example.core.domain.repositories

import com.example.network.module.image.Poster
import com.example.network.module.movie.Movie

interface MovieRepository {
    suspend fun getMovieByFilter(queryParameters: List<Pair<String, String>>): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie?
    suspend fun search(q: String, page: Int = 1): List<Movie>
    suspend fun getImages(movieId: Int, page: Int = 1): List<Poster>
}