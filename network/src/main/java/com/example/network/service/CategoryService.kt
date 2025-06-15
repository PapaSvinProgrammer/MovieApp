package com.example.network.service

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class CategoryService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getGenres(): Operation<List<Genre>, NetworkError> {
        return safeCall {
            client.get("v1/movie/possible-values-by-field?field=genres.name")
        }
    }

    suspend fun getMovieTypes(): Operation<List<MovieType>, NetworkError> {
        return safeCall {
            client.get("v1/movie/possible-values-by-field?field=type")
        }
    }

    suspend fun getCountries(): Operation<List<Country>, NetworkError> {
        return safeCall {
            client.get("v1/movie/possible-values-by-field?field=countries.name")
        }
    }
}