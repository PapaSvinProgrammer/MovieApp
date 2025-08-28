package com.mordva.login.presentation.widget.state

internal data class LoginUiState(
    val yandexAuthState: AuthState = AuthState.Init,
    val vkAuthState: AuthState = AuthState.Init
)