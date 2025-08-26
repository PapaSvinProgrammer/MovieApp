package com.mordva.search.presentation.searchResult.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.search.presentation.searchResult.SearchResultViewModel
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