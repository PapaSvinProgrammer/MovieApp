package com.mordva.network.internal.core

import com.mordva.util.error.ClientException
import com.mordva.util.error.ModelSerializationException
import com.mordva.util.error.ServerException
import com.mordva.util.error.UnknownException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
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