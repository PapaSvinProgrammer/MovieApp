package com.example.core.di

import com.example.core.data.repositories.CategoryRepositoryImpl
import com.example.core.data.repositories.CommentRepositoryImpl
import com.example.core.data.repositories.MovieRepositoryImpl
import com.example.core.data.repositories.SeasonRepositoryImpl
import com.example.core.domain.repositories.CategoryRepository
import com.example.core.domain.repositories.CommentRepository
import com.example.core.domain.repositories.MovieRepository
import com.example.core.domain.repositories.SeasonRepository
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
}