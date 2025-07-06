package com.example.network.internal.core

import android.util.Log
import com.example.common.ClientException
import com.example.common.ModelSerializationException
import com.example.common.ServerException
import com.example.common.UnknownException
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
                Log.d("RRRR", e.toString())
                Result.failure(ModelSerializationException())
            }
        }
        in 400..499 -> Result.failure(ClientException())
        in 500..599 -> Result.failure(ServerException())
        else -> Result.failure(UnknownException())
    }
}