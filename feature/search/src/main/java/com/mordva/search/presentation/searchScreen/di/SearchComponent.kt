 package com.mordva.search.presentation.searchScreen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

 @Component(
    modules = [SearchModule::class],
    dependencies = [SearchDependency::class]
)
@SearchScope
interface SearchComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: SearchDependency): SearchComponent
    }

     val viewModelFactory: ViewModelProvider.Factory
}