package com.example.network

import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType
import com.example.network.module.movie.Comment
import com.example.network.module.movie.Movie
import com.example.network.module.season.Season
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val LIMIT_API_COUNT = "20"
private const val LIMIT_API_MAX_COUNT = "250"

class KtorClient {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val client = HttpClient(OkHttp) {
        engine {
            preconfigured = okHttpClient
        }

        followRedirects = false

        defaultRequest {
            url("https://api.kinopoisk.dev/")
            header("accept", "application/json")
            header("X-API-KEY", BuildConfig.MOVIE_API_KEY)
        }

        install(Logging) {
            logger = Logger.DEFAULT
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getGenres(): List<Genre> {
        return try {
            client.get("v1/movie/possible-values-by-field?field=genres.name")
                .body()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getMovieTypes(): List<MovieType> {
        return try {
            client.get("v1/movie/possible-values-by-field?field=type")
                .body()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getCountries(): List<Country> {
        return try {
            client.get("v1/movie/possible-values-by-field?field=countries.name")
                .body()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getMovieById(movieId: Int): Movie? {
        return try {
            client.get("v1.4/movie/$movieId").body()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun searchMovieByName(page: Int, q: String): List<Movie> {
        return try {
            client.get("v1.4/movie/search") {
                url {
                    parameters.append("limit", LIMIT_API_COUNT)
                    parameters.append("page", page.toString())
                    parameters.append("query", q)
                }
            }.body()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getMoviesByFilter(queryParameters: Map<String, String>): List<Movie> {
        return try {
            client.get("v1.4/movie") {
                url {
                    parameters.append("limit", LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.key, it.value) }
                }
            }.body()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getCommentsByFilter(queryParameters: Map<String, String>): List<Comment> {
        return try {
            client.get("v1.4/review") {
                url {
                    parameters.append("limit", LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.key, it.value) }
                }
            }.body()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getSeasonsByMovie(movieId: Int): List<Season> {
        return try {
            client.get("v1.4/season") {
                url {
                    parameters.append("limit", LIMIT_API_MAX_COUNT)
                    parameters.append("movieId", movieId.toString())
                    parameters.append("sortField", "number")
                    parameters.append("sortType", "1")
                }
            }.body()
        } catch (e: Exception) {
            listOf()
        }
    }
}