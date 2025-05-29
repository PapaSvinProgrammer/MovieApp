package com.example.core.di

import com.example.core.data.repositories.AwardRepositoryImpl
import com.example.core.data.repositories.CategoryRepositoryImpl
import com.example.core.data.repositories.CollectionRepositoryImpl
import com.example.core.data.repositories.CommentRepositoryImpl
import com.example.core.data.repositories.MovieRepositoryImpl
import com.example.core.data.repositories.PersonRepositoryImpl
import com.example.core.data.repositories.PreferencesRepositoryImpl
import com.example.core.data.repositories.SeasonRepositoryImpl
import com.example.core.data.repositories.StudioRepositoryImpl
import com.example.core.domain.repositories.AwardRepository
import com.example.core.domain.repositories.CategoryRepository
import com.example.core.domain.repositories.CollectionRepository
import com.example.core.domain.repositories.CommentRepository
import com.example.core.domain.repositories.MovieRepository
import com.example.core.domain.repositories.PersonRepository
import com.example.core.domain.repositories.PreferencesRepository
import com.example.core.domain.repositories.SeasonRepository
import com.example.core.domain.repositories.StudioRepository
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
}