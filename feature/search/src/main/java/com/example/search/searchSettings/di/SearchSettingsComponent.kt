package com.example.search.searchSettings.di

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

    val viewModel: SearchSettingsViewModel
}