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
import com.example.utils.ApplicationScope
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface DataModuleImpl {
    @Binds
    @ApplicationScope
    fun bindCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @ApplicationScope
    fun binCommentRepositoryImpl(repository: CommentRepositoryImpl): CommentRepository

    @Binds
    @ApplicationScope
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    @ApplicationScope
    fun bindSeasonRepository(repository: SeasonRepositoryImpl): SeasonRepository

    @Binds
    @ApplicationScope
    fun bindAwardRepositoryImpl(repository: AwardRepositoryImpl): AwardRepository

    @Binds
    @ApplicationScope
    fun bindCollectionRepositoryImpl(repository: CollectionRepositoryImpl): CollectionRepository

    @Binds
    @ApplicationScope
    fun bindPersonRepositoryImpl(repository: PersonRepositoryImpl): PersonRepository

    @Binds
    @ApplicationScope
    fun bindStudioRepositoryImpl(repository: StudioRepositoryImpl): StudioRepository

    @Binds
    @ApplicationScope
    fun bindPreferencesRepositoryImpl(repository: PreferencesRepositoryImpl): PreferencesRepository

    @Binds
    @ApplicationScope
    fun bindHistoryRepositoryImpl(repository: HistoryRepositoryImpl): HistoryRepository
}