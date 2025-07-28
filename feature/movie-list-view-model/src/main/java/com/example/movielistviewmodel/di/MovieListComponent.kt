package com.example.movielistviewmodel.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [MovieListModule::class],
    dependencies = [AppComponent::class]
)
@MovieListScope
interface MovieListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MovieListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}