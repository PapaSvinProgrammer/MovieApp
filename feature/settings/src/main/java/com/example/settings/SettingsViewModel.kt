package com.example.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.external.PreferencesRepository
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
    var currentIcon by mutableIntStateOf(1)
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

    fun getCurrentIconIndex() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getCurrentIcon().collect {
                currentIcon = it
            }
        }
    }

    fun setCurrentIconIndex(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentIcon(index)
        }
    }
}