package com.example.movieScreen.di

import androidx.lifecycle.ViewModelProvider
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

    val viewModelFactory: ViewModelProvider.Factory
}