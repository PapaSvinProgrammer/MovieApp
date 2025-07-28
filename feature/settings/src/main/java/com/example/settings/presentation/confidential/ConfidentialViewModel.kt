package com.example.settings.presentation.confidential

import androidx.lifecycle.ViewModel
import com.example.data.external.PreferencesRepository
import javax.inject.Inject

class ConfidentialViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

}