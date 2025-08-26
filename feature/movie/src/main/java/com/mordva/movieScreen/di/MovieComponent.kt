package com.mordva.movieScreen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [MovieModule::class],
    dependencies = [MovieDependency::class]
)
@MovieScope
interface MovieComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: MovieDependency): MovieComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}