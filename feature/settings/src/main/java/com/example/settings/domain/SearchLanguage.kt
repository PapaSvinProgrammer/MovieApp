package com.example.settings.domain

import com.example.settings.utils.LanguageItem
import com.example.settings.utils.languagesList
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class SearchLanguage @Inject constructor(
) : UseCase<String, List<LanguageItem>>(Dispatchers.Default) {
    override suspend fun run(params: String): List<LanguageItem> {
        return languagesList.filter {
            it.text
                .lowercase()
                .trim()
                .contains(
                    params.lowercase().trim()
                )
        }
    }
}
