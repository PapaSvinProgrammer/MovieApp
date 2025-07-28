package com.example.settings.presentation.decor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.external.PreferencesRepository
import com.example.settings.presentation.widget.state.DecorUiState
import com.example.settings.utils.AppTheme
import com.example.settings.utils.ThemeObservable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DecorViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DecorUiState())
    val uiState = _uiState.asStateFlow()

    fun updateAlternativeSwitch(state: Boolean) {
        _uiState.update {
            it.copy(
                alternativeSwitch = state
            )
        }
    }

    fun setTheme(state: Int) {
        notifyThemeChanged(state)

        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setThemeState(state)
        }
    }

    fun getTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getThemeState().collect {
                _uiState.value = _uiState.value.copy(themeState = it)
            }
        }
    }

    fun getCurrentIconIndex() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getCurrentIcon().collect {
                _uiState.value = _uiState.value.copy(currentIcon = it)
            }
        }
    }

    fun setCurrentIconIndex(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setCurrentIcon(index)
        }
    }

    private fun notifyThemeChanged(state: Int) {
        when (state) {
            1 -> ThemeObservable.notify(AppTheme.SYSTEM)
            2 -> ThemeObservable.notify(AppTheme.DARK)
            else -> ThemeObservable.notify(AppTheme.LIGHT)
        }
    }
}
