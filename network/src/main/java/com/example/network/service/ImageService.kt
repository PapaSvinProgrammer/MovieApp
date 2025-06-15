package com.example.network.service

import com.example.network.core.LIMIT_API_COUNT
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.module.image.Docs
import com.example.network.module.image.Poster
import com.example.network.utils.Constants.LIMIT_FIELD
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class ImageService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getMovieImages(
        movieId: Int,
        page: Int = 1
    ): Operation<Docs<Poster>, NetworkError> {
        return safeCall {
            client.get("v1.4/image") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(MOVIE_ID_FIELD, movieId.toString())
                }
            }
        }
    }
}