package com.example.network.internal.core

import com.example.utils.error.ClientException
import com.example.utils.error.ModelSerializationException
import com.example.utils.error.ServerException
import com.example.utils.error.UnknownException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

internal suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.success(response.body<T>())
            } catch (e: Exception) {
                Result.failure(ModelSerializationException())
            }
        }
        in 400..499 -> Result.failure(ClientException())
        in 500..599 -> Result.failure(ServerException())
        else -> Result.failure(UnknownException())
    }
}