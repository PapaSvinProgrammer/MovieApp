package com.example.room.internal.utils

import com.example.utils.error.RoomThrowable

suspend inline fun <reified T> safeCall(
    execute: suspend () -> T
): Result<T> {
    return try {
        Result.success(execute())
    } catch (e: Throwable) {
        Result.failure(RoomThrowable(e.message.toString()))
    }
}