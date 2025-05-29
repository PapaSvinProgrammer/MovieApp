package com.example.movieapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.repositories.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    fun setEntryState(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setEntryState(state)
        }
    }
}