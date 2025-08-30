package com.mordva.login.presentation.widget.state

internal sealed interface AuthState {
    data object Init : AuthState
    data object Success : AuthState
    data object Error : AuthState
    data object Loading : AuthState
}