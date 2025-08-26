package com.mordva.settings.presentation.sound

import androidx.lifecycle.ViewModel
import com.mordva.domain.repository.PreferencesRepository
import com.mordva.settings.presentation.widget.state.SoundUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class SoundViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SoundUiState())
    val uiState = _uiState.asStateFlow()

    fun updateVibrationSwitch(state: Boolean) {
        _uiState.update {
            it.copy(vibrationSwitch = state)
        }
    }
}