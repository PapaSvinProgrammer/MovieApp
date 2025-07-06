package com.example.movielistviewmodel.di

import androidx.lifecycle.ViewModelProvider
import com.example.movielistviewmodel.MovieListViewModel
import dagger.Component

@Component(
    modules = [MovieListModule::class]
)
@MovieListScope
interface MovieListComponent {
    @Component.Factory
    interface Factory {
        fun create(): MovieListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}