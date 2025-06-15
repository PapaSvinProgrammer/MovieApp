package com.example.network.service

import com.example.network.core.LIMIT_API_COUNT
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.module.image.Docs
import com.example.network.module.movie.Studio
import com.example.network.utils.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class StudiesService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Studio>, NetworkError> {
        return safeCall {
            client.get("v1.4/studio") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }
    }
}