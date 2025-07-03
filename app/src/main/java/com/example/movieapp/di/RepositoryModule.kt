package com.example.movieapp.di

import com.example.data.external.AwardRepository
import com.example.data.external.CategoryRepository
import com.example.data.external.CollectionRepository
import com.example.data.external.CommentRepository
import com.example.data.external.HistoryRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository
import com.example.data.external.PreferencesRepository
import com.example.data.external.SeasonRepository
import com.example.data.external.StudioRepository
import com.example.data.internal.AwardRepositoryImpl
import com.example.data.internal.CategoryRepositoryImpl
import com.example.data.internal.CollectionRepositoryImpl
import com.example.data.internal.CommentRepositoryImpl
import com.example.data.internal.HistoryRepositoryImpl
import com.example.data.internal.MovieRepositoryImpl
import com.example.data.internal.PersonRepositoryImpl
import com.example.data.internal.PreferencesRepositoryImpl
import com.example.data.internal.SeasonRepositoryImpl
import com.example.data.internal.StudioRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun binCommentRepositoryImpl(repository: CommentRepositoryImpl): CommentRepository

    @Binds
    @Singleton
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    fun bindSeasonRepository(repository: SeasonRepositoryImpl): SeasonRepository

    @Binds
    @Singleton
    fun bindAwardRepositoryImpl(repository: AwardRepositoryImpl): AwardRepository

    @Binds
    @Singleton
    fun bindCollectionRepositoryImpl(repository: CollectionRepositoryImpl): CollectionRepository

    @Binds
    @Singleton
    fun bindPersonRepositoryImpl(repository: PersonRepositoryImpl): PersonRepository

    @Binds
    @Singleton
    fun bindStudioRepositoryImpl(repository: StudioRepositoryImpl): StudioRepository

    @Binds
    @Singleton
    fun bindPreferencesRepositoryImpl(repository: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    @Singleton
    fun bindHistoryRepositoryImpl(repository: HistoryRepositoryImpl): HistoryRepository
}