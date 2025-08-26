package com.mordva.search.presentation.searchResult.di

import androidx.lifecycle.ViewModelProvider
import com.mordva.search.presentation.searchScreen.di.SearchDependency
import dagger.Component

@Component(
    modules = [SearchResultModule::class],
    dependencies = [SearchDependency::class]
)
@SearchResultScope
interface SearchResultComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: SearchDependency): SearchResultComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}