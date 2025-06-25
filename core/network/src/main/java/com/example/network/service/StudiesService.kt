package com.example.network.service

import com.example.network.core.LIMIT_API_COUNT
import com.example.network.core.safeCall
import com.example.network.model.image.Docs
import com.example.network.model.movie.Studio
import com.example.network.utils.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class StudiesService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getStudies(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Studio>> {
        return safeCall<Docs<Studio>> {
            client.get("v1.4/studio") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { it.docs }
    }
}