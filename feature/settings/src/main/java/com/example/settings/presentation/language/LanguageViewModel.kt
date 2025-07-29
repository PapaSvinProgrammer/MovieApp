package com.example.settings.presentation.language

import androidx.lifecycle.ViewModel
import com.example.settings.domain.SearchLanguage
import com.example.settings.presentation.widget.state.LanguageUiState
import com.example.settings.utils.languagesList
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class LanguageViewModel @Inject constructor(
    private val searchLanguage: SearchLanguage
) : ViewModel() {
    private val _uiState = MutableStateFlow(LanguageUiState())
    val uiState = _uiState.asStateFlow()

    fun updateQuery(q: String) {
        _uiState.update {
            it.copy(query = q)
        }
    }

    fun updateDefaultLanguage(slug: String) {
        _uiState.update {
            it.copy(defaultLanguageSlug = slug)
        }
    }

    fun getLanguages() = launchWithoutOld(SEARCH_LANGUAGES_JOB) {
        if (_uiState.value.query.isEmpty()) {
            _uiState.update {
                it.copy(languages = languagesList)
            }
        } else {
            _uiState.update {
                it.copy(languages = searchLanguage.execute(it.query))
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val SEARCH_LANGUAGES_JOB = "search_languages_by_name"
    }
}
