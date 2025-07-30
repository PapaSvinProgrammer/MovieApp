package com.example.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

suspend fun <T> multiRequest(
    list: List<String>,
    execute: suspend (String) -> Result<T>
): List<T> {
    val tasks = mutableListOf<Deferred<Result<T>>>()

    list.forEach { id ->
        val task = CoroutineScope(Dispatchers.IO).async {
            execute(id)
        }

        tasks.add(task)
    }

    val result = mutableListOf<T>()

    tasks.awaitAll().forEach {
        it.onSuccess { data ->
            result.add(data)
        }
    }

    return result
}
