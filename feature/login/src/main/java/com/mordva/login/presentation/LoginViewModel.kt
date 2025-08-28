package com.mordva.login.presentation

import androidx.lifecycle.ViewModel
import com.mordva.login.presentation.widget.state.AuthState
import com.mordva.login.presentation.widget.state.LoginUiState
import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import com.mordva.util.launchWithoutOld
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.yandex.authsdk.YandexAuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(
    private val securityRepository: SecurityRepository
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

    fun handleYandexAuth(result: YandexAuthResult) {
        when (result) {
            YandexAuthResult.Cancelled -> updateYandexAuthState(AuthState.Init)
            is YandexAuthResult.Failure -> updateYandexAuthState(AuthState.Error)
            is YandexAuthResult.Success -> saveYandexToken(result.token.value)
        }
    }

    fun authWithVk() = launchWithoutOld(AUTH_JOB) {
        val callback = object : VKIDAuthCallback {
            override fun onAuth(accessToken: AccessToken) {
                saveVkToken(accessToken.token)
                updateYandexAuthState(AuthState.Success)
            }

            override fun onFail(fail: VKIDAuthFail) {
                updateYandexAuthState(AuthState.Error)
            }
        }

        VKID.instance.authorize(callback)
    }

    private fun saveYandexToken(token: String) = launchWithoutOld(SAVE_TOKEN) {
        securityRepository.setData(
            data = token,
            type = SecurityType.YANDEX
        ).onSuccess {
            updateYandexAuthState(AuthState.Success)
        }
    }

    private fun saveVkToken(token: String) = launchWithoutOld(SAVE_TOKEN) {
        securityRepository.setData(
            data = token,
            type = SecurityType.VK
        ).onSuccess {
            updateVkAuthState(AuthState.Success)
        }
    }

    private companion object {
        const val AUTH_JOB = "auth"
        const val SAVE_TOKEN = "save_token"
    }
}
