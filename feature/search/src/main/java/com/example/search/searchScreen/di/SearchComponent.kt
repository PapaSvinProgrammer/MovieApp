package com.example.search.searchScreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [SearchModule::class],
    dependencies = [AppComponent::class]
)
@SearchScope
interface SearchComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SearchComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}