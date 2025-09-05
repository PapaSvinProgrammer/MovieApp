package com.mordva.data.di

import com.mordva.data.AwardRepositoryImpl
import com.mordva.data.CategoryRepositoryImpl
import com.mordva.data.CollectionRepositoryImpl
import com.mordva.data.CommentRepositoryImpl
import com.mordva.data.FavoritePackageRepositoryImpl
import com.mordva.data.HistoryRepositoryImpl
import com.mordva.data.MovieRepositoryImpl
import com.mordva.data.PersonRepositoryImpl
import com.mordva.data.PreferencesRepositoryImpl
import com.mordva.data.RatedMovieRepositoryImpl
import com.mordva.data.SeasonRepositoryImpl
import com.mordva.data.StudioRepositoryImpl
import com.mordva.data.WillWatchPackageRepositoryImpl
import com.mordva.domain.repository.AwardRepository
import com.mordva.domain.repository.CategoryRepository
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.domain.repository.HistoryRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.repository.PreferencesRepository
import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.domain.repository.SeasonRepository
import com.mordva.domain.repository.StudioRepository
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.util.ApplicationScope
import dagger.Binds
import dagger.Module

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

    @Binds
    @ApplicationScope
    fun bindsRatedMovieRepository(repository: RatedMovieRepositoryImpl): RatedMovieRepository

    @Binds
    @ApplicationScope
    fun bindsWillWatchPackageRepositoryImpl(repository: WillWatchPackageRepositoryImpl): WillWatchPackageRepository

    @Binds
    @ApplicationScope
    fun bindsFavoritePackageRepositoryImpl(repository: FavoritePackageRepositoryImpl): FavoritePackageRepository
}