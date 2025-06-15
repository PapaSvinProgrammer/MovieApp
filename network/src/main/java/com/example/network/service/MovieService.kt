package com.example.network.service

import com.example.network.core.LIMIT_API_COUNT
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.module.image.Docs
import com.example.network.module.movie.Movie
import com.example.network.utils.Constants.LIMIT_FIELD
import com.example.network.utils.Constants.NAME_FIELD
import com.example.network.utils.Constants.NOT_NULL_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.QUERY_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class MovieService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getMovieById(movieId: Int): Operation<Movie, NetworkError> {
        return safeCall {
            client.get("v1.4/movie/$movieId")
        }
    }

    suspend fun searchMovieByName(page: Int, q: String): Operation<Docs<Movie>, NetworkError> {
        return safeCall {
            client.get("v1.4/movie/search") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(QUERY_FIELD, q)
                }
            }
        }
    }

    suspend fun getMoviesByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Movie>, NetworkError> {
        return safeCall {
            client.get("v1.4/movie") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(NOT_NULL_FIELD, NAME_FIELD)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }
    }
}