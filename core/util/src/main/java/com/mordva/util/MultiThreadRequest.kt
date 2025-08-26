package com.mordva.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

suspend fun <ResponseParams, RequestParams> multiRequest(
    list: List<RequestParams>,
    execute: suspend (RequestParams) -> Result<ResponseParams>
): List<ResponseParams> {
    val tasks = mutableListOf<Deferred<Result<ResponseParams>>>()

    list.forEach { id ->
        val task = CoroutineScope(Dispatchers.IO).async {
            execute(id)
        }

        tasks.add(task)
    }

    val result = mutableListOf<ResponseParams>()

    tasks.awaitAll().forEach {
        it.onSuccess { data ->
            result.add(data)
        }
    }

    return result
}
