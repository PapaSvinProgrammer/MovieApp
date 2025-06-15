package com.example.network.core

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Operation<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Operation.Success(response.body<T>())
            } catch (e: Exception) {
                Operation.Error(NetworkError.SERIALIZATION)
            }
        }
        in 400..499 -> Operation.Error(NetworkError.CLIENT_ERROR)
        in 500..599 -> Operation.Error(NetworkError.SERVER_ERROR)
        else -> Operation.Error(NetworkError.UNKNOWN)
    }
}