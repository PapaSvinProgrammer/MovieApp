package com.example.network.service

import com.example.network.core.safeCall
import com.example.network.model.category.Country
import com.example.network.model.category.Genre
import com.example.network.model.category.MovieType
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class CategoryService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getGenres(): Result<List<Genre>> {
        return safeCall {
            client.get("v1/movie/possible-values-by-field?field=genres.name")
        }
    }

    suspend fun getMovieTypes(): Result<List<MovieType>> {
        return safeCall {
            client.get("v1/movie/possible-values-by-field?field=type")
        }
    }

    suspend fun getCountries(): Result<List<Country>> {
        return safeCall {
            client.get("v1/movie/possible-values-by-field?field=countries.name")
        }
    }
}