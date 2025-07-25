package com.example.movielistviewmodel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.MovieRepository
import com.example.data.internal.di.DataModule
import com.example.movieScreen.GetMovieByFilter
import com.example.movielistviewmodel.MovieListViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
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