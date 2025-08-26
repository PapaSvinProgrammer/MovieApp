package com.mordva.room.internal.utils

import com.mordva.util.error.RoomThrowable

suspend inline fun <reified T> safeCall(
    execute: suspend () -> T
): Result<T> {
    return try {
        Result.success(execute())
    } catch (e: Throwable) {
        Result.failure(RoomThrowable(e.message.toString()))
    }
}