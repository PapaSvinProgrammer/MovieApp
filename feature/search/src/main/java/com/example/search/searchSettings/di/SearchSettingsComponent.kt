package com.example.search.searchSettings.di

import androidx.lifecycle.ViewModelProvider
import com.example.search.searchScreen.di.SearchDependency
import dagger.Component

@Component(
    modules = [SearchSettingsModule::class],
    dependencies = [SearchDependency::class]
)
@SearchSettingsScope
interface SearchSettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: SearchDependency): SearchSettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}