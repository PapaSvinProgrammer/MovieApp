package com.example.search.searchSettings.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [SearchSettingsModule::class],
    dependencies = [AppComponent::class]
)
@SearchSettingsScope
interface SearchSettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SearchSettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}