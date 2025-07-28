package com.example.search.searchResult.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.MovieRepository
import com.example.data.internal.di.DataModule
import com.example.movieScreen.GetMovieByFilter
import com.example.search.searchResult.SearchResultViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface SearchResultModule {
    @Binds
    @SearchResultScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SearchResultScope
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    fun bindsViewModelSearchResultViewModel(viewModel: SearchResultViewModel): ViewModel

    companion object {
        @Provides
        @SearchResultScope
        fun providesGetMovieByFilter(repository: MovieRepository): GetMovieByFilter {
            return GetMovieByFilter(repository)
        }
    }
}