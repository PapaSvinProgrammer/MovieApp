package com.mordva.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out Result>(private val dispatcher: CoroutineDispatcher) {
    suspend fun execute(params: Params): Result = withContext(dispatcher) {
        run(params)
    }

    protected abstract suspend fun run(params: Params): Result
}