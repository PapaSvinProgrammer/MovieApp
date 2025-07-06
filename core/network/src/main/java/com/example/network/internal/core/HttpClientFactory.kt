package com.example.network.internal.core

import com.example.movieapp.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

const val LIMIT_API_COUNT = "20"

object HttpClientFactory {
    fun create(okHttpClient: OkHttpClient): HttpClient {
        return HttpClient(OkHttp) {
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
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}