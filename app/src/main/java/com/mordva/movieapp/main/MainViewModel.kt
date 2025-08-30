package com.mordva.movieapp.main

import androidx.lifecycle.ViewModel
import com.mordva.domain.repository.PreferencesRepository
import com.mordva.navigation.HomeGraph
import com.mordva.navigation.LoginGraph
import com.mordva.navigation.OtpGraph
import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import com.mordva.settings.utils.toAppTheme
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val securityRepository: SecurityRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        getTheme()
        getAuthState()
    }

    private fun getTheme() = launchWithoutOld(GET_THEME_JOB) {
        preferencesRepository.getThemeState().collect { theme ->
            _state.update {
                it.copy(theme = theme.toAppTheme())
            }
        }
    }

    private fun getAuthState() = launchWithoutOld(GET_ROUTE_JOB) {
        val tokenRes = securityRepository.tokenIsExist()

        val startRoute = when (tokenRes) {
            SecurityType.YANDEX -> HomeGraph
            SecurityType.VK -> HomeGraph
            SecurityType.PASSWORD -> OtpGraph
            null -> LoginGraph
        }

        _state.update {
            it.copy(startRoute = startRoute)
        }
    }

    private companion object {
        const val GET_THEME_JOB = "get_theme"
        const val GET_ROUTE_JOB = "get_start_route"
    }
}