package com.example.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.collectionusecase.GetCollectionAll
import com.example.data.external.CollectionRepository
import com.example.data.external.MovieRepository
import com.example.home.domain.GetMoviesByCollection
import com.example.home.domain.GetMoviesByCompany
import com.example.home.domain.GetMoviesByGenre
import com.example.home.presentation.HomeViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
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