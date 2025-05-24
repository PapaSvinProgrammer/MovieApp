package com.example.network

import com.example.network.module.Genre
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
        return client
            .get("v1/movie/possible-values-by-field?field=genres.name")
            .body()
    }
}