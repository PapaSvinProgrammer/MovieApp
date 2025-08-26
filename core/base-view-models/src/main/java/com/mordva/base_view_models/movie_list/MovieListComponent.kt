package com.mordva.base_view_models.movie_list

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [MovieListModule::class],
    dependencies = [MovieListDependency::class]
)
@MovieListScope
interface MovieListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: MovieListDependency): MovieListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}