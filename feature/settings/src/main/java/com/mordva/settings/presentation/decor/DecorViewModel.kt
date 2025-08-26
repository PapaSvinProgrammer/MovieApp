package com.mordva.settings.presentation.decor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mordva.domain.repository.PreferencesRepository
import com.mordva.settings.presentation.widget.state.DecorUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DecorViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DecorUiState())
    val uiState = _uiState.asStateFlow()

    fun setTheme(state: Int) {
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
}
