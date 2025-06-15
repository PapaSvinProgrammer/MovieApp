package com.example.network.core

typealias RootError = Error

sealed interface Operation<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): Operation<D, E>
    data class Error<out D, out E: RootError>(val error: E): Operation<D, E>

    fun onSuccess(block: (D) -> Unit): Operation<D, E> {
        if (this is Success) block(data)
        return this
    }

    fun onError(block: (E) -> Unit): Operation<D, E> {
        if (this is Error) block(error)
        return this
    }
}