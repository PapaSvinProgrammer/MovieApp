package com.mordva.login.presentation

import androidx.lifecycle.ViewModel
import com.mordva.login.presentation.widget.state.AuthState
import com.mordva.login.presentation.widget.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun updateYandexAuthState(state: AuthState) {
        _state.update {
            it.copy(yandexAuthState = state)
        }
    }

    fun updateVkAuthState(state: AuthState) {
        _state.update {
            it.copy(vkAuthState = state)
        }
    }
}
