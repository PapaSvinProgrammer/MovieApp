package com.example.core.di

import com.example.core.data.repositoriesImpl.AwardRepositoryImpl
import com.example.core.data.repositoriesImpl.CategoryRepositoryImpl
import com.example.core.data.repositoriesImpl.CollectionRepositoryImpl
import com.example.core.data.repositoriesImpl.CommentRepositoryImpl
import com.example.core.data.repositoriesImpl.HistoryRepositoryImpl
import com.example.core.data.repositoriesImpl.MovieRepositoryImpl
import com.example.core.data.repositoriesImpl.PersonRepositoryImpl
import com.example.core.data.repositoriesImpl.PreferencesRepositoryImpl
import com.example.core.data.repositoriesImpl.SeasonRepositoryImpl
import com.example.core.data.repositoriesImpl.StudioRepositoryImpl
import com.example.core.data.repositories.AwardRepository
import com.example.core.data.repositories.CategoryRepository
import com.example.core.data.repositories.CollectionRepository
import com.example.core.data.repositories.CommentRepository
import com.example.core.data.repositories.HistoryRepository
import com.example.core.data.repositories.MovieRepository
import com.example.core.data.repositories.PersonRepository
import com.example.core.data.repositories.PreferencesRepository
import com.example.core.data.repositories.SeasonRepository
import com.example.core.data.repositories.StudioRepository
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