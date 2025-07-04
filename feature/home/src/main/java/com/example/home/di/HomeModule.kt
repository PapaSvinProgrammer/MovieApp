package com.example.home.di

import com.example.collectionusecase.GetCollectionAll
import com.example.data.external.CollectionRepository
import com.example.data.external.MovieRepository
import com.example.home.domain.GetMoviesByCollection
import com.example.home.domain.GetMoviesByCompany
import com.example.home.domain.GetMoviesByGenre
import dagger.Module
import dagger.Provides

@Module
interface HomeModule {
    companion object {
        @Provides
        fun providesGetMoviesByCollection(repository: MovieRepository): GetMoviesByCollection {
            return GetMoviesByCollection(repository)
        }

        @Provides
        fun providesGetMoviesByCompany(repository: MovieRepository): GetMoviesByCompany {
            return GetMoviesByCompany(repository)
        }

        @Provides
        fun providesGetMoviesByGenre(repository: MovieRepository): GetMoviesByGenre {
            return GetMoviesByGenre(repository)
        }

        @Provides
        fun providesGetCollectionAll(repository: CollectionRepository): GetCollectionAll {
            return GetCollectionAll(repository)
        }
    }
}