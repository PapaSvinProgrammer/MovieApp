package com.example.search.searchSettings.di

import androidx.lifecycle.ViewModelProvider
import com.example.search.searchSettings.SearchSettingsViewModel
import dagger.Component

@Component(
    modules = [SearchSettingsModule::class]
)
@SearchSettingsScope
interface SearchSettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(): SearchSettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}