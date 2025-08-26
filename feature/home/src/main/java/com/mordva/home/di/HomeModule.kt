package com.mordva.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.home.domain.GetMoviesByCollection
import com.mordva.home.domain.GetMoviesByCompany
import com.mordva.home.domain.GetMoviesByGenre
import com.mordva.home.presentation.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface HomeModule {
    @Binds
    @HomeScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @HomeScope
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

    companion object {
        @Provides
        @HomeScope
        fun providesGetMoviesByGenre(repository: MovieRepository): GetMoviesByGenre {
            return GetMoviesByGenre(repository)
        }

        @Provides
        @HomeScope
        fun providesGetMoviesByCollection(repository: MovieRepository): GetMoviesByCollection {
            return GetMoviesByCollection(repository)
        }

        @Provides
        @HomeScope
        fun providesGetCollectionAll(repository: CollectionRepository): GetCollectionAll {
            return GetCollectionAll(repository)
        }

        @Provides
        @HomeScope
        fun providesGetMoviesByCompany(repository: MovieRepository): GetMoviesByCompany {
            return GetMoviesByCompany(repository)
        }
    }
}