package com.mordva.login.presentation.widget.state

internal sealed interface AuthState {
    data object Init : AuthState
    data class Success(private val token: String) : AuthState
    data object Error : AuthState
    data object Loading : AuthState
}