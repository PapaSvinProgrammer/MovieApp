package com.mordva.base_view_models.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.movie.GetMovieByFilter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface MovieListModule {
    @Binds
    @MovieListScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @MovieListScope
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    fun bindsMovieListViewModel(viewModel: MovieListViewModel): ViewModel

    companion object {
        @Provides
        @MovieListScope
        fun providesGetMovieByFilter(repository: MovieRepository): GetMovieByFilter {
            return GetMovieByFilter(repository)
        }
    }
}