package com.example.data.internal.di

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
internal interface DataModuleImpl {
    @Binds
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun binCommentRepositoryImpl(repository: CommentRepositoryImpl): CommentRepository

    @Binds
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun bindSeasonRepository(repository: SeasonRepositoryImpl): SeasonRepository

    @Binds
    fun bindAwardRepositoryImpl(repository: AwardRepositoryImpl): AwardRepository

    @Binds
    fun bindCollectionRepositoryImpl(repository: CollectionRepositoryImpl): CollectionRepository

    @Binds
    fun bindPersonRepositoryImpl(repository: PersonRepositoryImpl): PersonRepository

    @Binds
    fun bindStudioRepositoryImpl(repository: StudioRepositoryImpl): StudioRepository

    @Binds
    fun bindPreferencesRepositoryImpl(repository: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    fun bindHistoryRepositoryImpl(repository: HistoryRepositoryImpl): HistoryRepository
}