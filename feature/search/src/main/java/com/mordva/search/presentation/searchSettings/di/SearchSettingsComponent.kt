package com.mordva.search.presentation.searchSettings.di

import androidx.lifecycle.ViewModelProvider
import com.mordva.search.presentation.searchScreen.di.SearchDependency
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