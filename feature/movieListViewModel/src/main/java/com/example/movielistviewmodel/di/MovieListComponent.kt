package com.example.movielistviewmodel.di

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

    val viewModel: MovieListViewModel
}