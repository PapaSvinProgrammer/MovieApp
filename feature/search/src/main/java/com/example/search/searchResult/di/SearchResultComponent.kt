package com.example.search.searchResult.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import com.example.search.searchResult.SearchResultViewModel
import dagger.Component

@Component(
    modules = [SearchResultModule::class],
    dependencies = [AppComponent::class]
)
@SearchResultScope
interface SearchResultComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SearchResultComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}