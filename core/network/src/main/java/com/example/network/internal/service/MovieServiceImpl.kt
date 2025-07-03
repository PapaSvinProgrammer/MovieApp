package com.example.network.internal.service

import com.example.common.Constants.LIMIT_FIELD
import com.example.common.Constants.MOVIE_ID_FIELD
import com.example.common.Constants.NAME_FIELD
import com.example.common.Constants.NOT_NULL_FIELD
import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.QUERY_FIELD
import com.example.model.image.Poster
import com.example.model.movie.Movie
import com.example.network.internal.model.image.PosterDto
import com.example.network.internal.model.movie.MovieDto
import com.example.network.external.MovieService
import com.example.network.internal.core.LIMIT_API_COUNT
import com.example.network.internal.core.safeCall
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.model.image.Docs
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class MovieServiceImpl @Inject constructor(
    private val client: HttpClient
) : MovieService {
    override suspend fun getMovieById(movieId: Int): Result<Movie> {
        return safeCall<MovieDto> {
            client.get("v1.4/movie/$movieId")
        }.map { it.toDomain() }
    }

    override suspend fun searchMovieByName(page: Int, q: String): Result<List<Movie>> {
        return safeCall<Docs<MovieDto>> {
            client.get("v1.4/movie/search") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(QUERY_FIELD, q)
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }

    override suspend fun getMoviesByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Movie>> {
        return safeCall<Docs<MovieDto>> {
            client.get("v1.4/movie") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(NOT_NULL_FIELD, NAME_FIELD)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }

    override suspend fun getMovieImages(
        movieId: Int,
        page: Int
    ): Result<List<Poster>> {
        return safeCall<Docs<PosterDto>> {
            client.get("v1.4/image") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(MOVIE_ID_FIELD, movieId.toString())
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }
}