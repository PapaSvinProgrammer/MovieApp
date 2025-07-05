package com.example.network.internal.core

import com.example.common.ModelSerializationException
import com.example.common.NoInternetException
import com.example.common.UnknownException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

internal suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T> {
    val response = try {
        execute()
    } catch (e:  UnresolvedAddressException) {
        return Result.failure(NoInternetException())
    } catch (e: SerializationException) {
        return Result.failure(ModelSerializationException())
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.failure(UnknownException())
    }

    return responseToResult(response)
}