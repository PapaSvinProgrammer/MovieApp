package com.example.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.external.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _pinCodeSwitch = MutableStateFlow(false)
    private val _vibrationSwitch = MutableStateFlow(false)
    private val _alternativeIconSwitch = MutableStateFlow(false)
    private val _isDark = MutableStateFlow(false)
    private var _currentIcon = MutableStateFlow(1)

    val pinCodeSwitch: StateFlow<Boolean> = _pinCodeSwitch
    val vibrationSwitch: StateFlow<Boolean> = _vibrationSwitch
    val alternativeIconSwitch: StateFlow<Boolean> = _alternativeIconSwitch
    val isDark: StateFlow<Boolean> = _isDark
    val currentIcon: StateFlow<Int> = _currentIcon

    fun updatePinSwitch(state: Boolean) {
        _pinCodeSwitch.value = state
    }

    fun updateVibrationSwitch(state: Boolean) {
        _vibrationSwitch.value = state
    }

    fun updateAlternativeSwitch(state: Boolean) {
        _alternativeIconSwitch.value = state
    }

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setDarkTheme(isDark)
        }
    }

    fun getTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getDarkTheme().collect {
                _isDark.value = it
            }
        }
    }

    fun getCurrentIconIndex() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getCurrentIcon().collect {
                _currentIcon.value = it
            }
        }
    }

    fun setCurrentIconIndex(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentIcon(index)
        }
    }
}