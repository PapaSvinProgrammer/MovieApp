package com.example.network.service

import com.example.network.core.LIMIT_API_COUNT
import com.example.network.core.safeCall
import com.example.network.model.image.Docs
import com.example.network.model.season.Season
import com.example.network.utils.Constants.LIMIT_FIELD
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class SeasonService @Inject constructor(
    private val client: HttpClient
){
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>> {
        return safeCall<Docs<Season>> {
            client.get("v1.4/season") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(MOVIE_ID_FIELD, movieId.toString())
                    parameters.append(SORT_FIELD, "number")
                    parameters.append(SORT_TYPE, "1")
                }
            }
        }.map { it.docs }
    }
}