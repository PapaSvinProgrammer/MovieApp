package com.example.movieapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.external.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    var isEntry by mutableStateOf(false)
        private set
    var darkTheme by mutableStateOf(true)
        private set

    fun getEntryState() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getEntryState().collect {
                isEntry = it
            }
        }
    }

    fun getDarkThemeState() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getDarkTheme().collect {
                darkTheme = it
            }
        }
    }
}