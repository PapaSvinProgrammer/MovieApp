package com.example.movieScreen.di

import com.example.movieScreen.MovieViewModel
import dagger.Component

@Component(
    modules = [MovieModule::class]
)
@MovieScope
interface MovieComponent {
    @Component.Factory
    interface Factory {
        fun create(): MovieComponent
    }

    val viewModel: MovieViewModel
}