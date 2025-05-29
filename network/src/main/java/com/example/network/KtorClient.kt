package com.example.network

import com.example.network.utils.Constants.LIMIT_FIELD
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.QUERY_FIELD
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.MovieType
import com.example.network.module.image.Collection
import com.example.network.module.image.Docs
import com.example.network.module.movie.Comment
import com.example.network.module.movie.Movie
import com.example.network.module.movie.Studio
import com.example.network.module.person.NominationAward
import com.example.network.module.person.Person
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

private const val LIMIT_API_COUNT = "20"
private const val LIMIT_API_MAX_COUNT = "250"

class KtorClient(okHttpClient: OkHttpClient) {
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
                    explicitNulls = false
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getGenres(): List<Genre> {
        return client.get("v1/movie/possible-values-by-field?field=genres.name")
            .body()
    }

    suspend fun getMovieTypes(): List<MovieType> {
        return client
            .get("v1/movie/possible-values-by-field?field=type")
            .body()
    }

    suspend fun getCountries(): List<Country> {
        return client.get("v1/movie/possible-values-by-field?field=countries.name")
            .body()
    }

    suspend fun getMovieById(movieId: Int): Movie? {
        return client.get("v1.4/movie/$movieId").body()
    }

    suspend fun searchMovieByName(page: Int, q: String): List<Movie> {
        val res: Docs<Movie> = client.get("v1.4/movie/search") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                parameters.append(PAGE_FIELD, page.toString())
                parameters.append(QUERY_FIELD, q)
            }
        }.body()

        return res.docs
    }

    suspend fun getMoviesByFilter(queryParameters: Map<String, String>): List<Movie> {
        val res: Docs<Movie> = client.get("v1.4/movie") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }

    suspend fun getCommentsByFilter(queryParameters: Map<String, String>): List<Comment> {
        val res: Docs<Comment> = client.get("v1.4/review") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }

    suspend fun getSeasonsByMovie(movieId: Int): List<Season> {
        val res: Docs<Season> = client.get("v1.4/season") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_MAX_COUNT)
                parameters.append(MOVIE_ID_FIELD, movieId.toString())
                parameters.append(SORT_FIELD, "number")
                parameters.append(SORT_TYPE, "1")
            }
        }.body()

        return res.docs
    }

    suspend fun getStudies(queryParameters: Map<String, String>): List<Studio> {
        val res: Docs<Studio> = client.get("v1.4/studio") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }

    suspend fun getPersonById(personId: Int): Person? {
        return client.get("v1.4/person/$personId").body()
    }

    suspend fun searchPersonByName(q: String, page: Int): List<Person> {
        val res: Docs<Person> = client.get("v1.4/person/search") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                parameters.append(QUERY_FIELD, q)
                parameters.append(PAGE_FIELD, page.toString())
            }
        }.body()

        return res.docs
    }

    suspend fun getPersonByFilter(queryParameters: Map<String, String>): List<Person> {
        val res: Docs<Person> = client.get("v1.4/person") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }

    suspend fun getPersonAwardsByFilter(queryParameters: Map<String, String>): List<NominationAward> {
        val res: Docs<NominationAward> = client.get("v1.4/person/awards") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }

    suspend fun getMovieAwardsByFilter(queryParameters: Map<String, String>): List<NominationAward> {
        val res: Docs<NominationAward> = client.get("v1.4/movie/awards") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }

    suspend fun getCollectionByFilter(queryParameters: Map<String, String>): List<Collection> {
        val res: Docs<Collection> = client.get("v1.4/list") {
            url {
                parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                queryParameters.forEach { parameters.append(it.key, it.value) }
            }
        }.body()

        return res.docs
    }
}