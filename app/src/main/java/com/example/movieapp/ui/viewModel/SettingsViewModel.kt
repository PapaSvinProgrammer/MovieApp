package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.repositories.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    var pinCodeSwitch by mutableStateOf(false)
        private set
    var vibrationSwitch by mutableStateOf(false)
        private set
    var alternativeIconSwitch by mutableStateOf(false)
        private set
    var isDark by mutableStateOf(true)
        private set

    fun updatePinSwitch(state: Boolean) {
        pinCodeSwitch = state
    }

    fun updateVibrationSwitch(state: Boolean) {
        vibrationSwitch = state
    }

    fun updateAlternativeSwitch(state: Boolean) {
        alternativeIconSwitch = state
    }

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setDarkTheme(isDark)
        }
    }

    fun getTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getDarkTheme().collect {
                isDark = it
            }
        }
    }
}