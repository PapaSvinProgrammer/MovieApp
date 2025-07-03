package com.example.movieapp.di

import com.example.network.external.AwardService
import com.example.network.external.CategoryService
import com.example.network.external.CollectionService
import com.example.network.external.CommentService
import com.example.network.external.MovieService
import com.example.network.external.PersonService
import com.example.network.external.SeasonService
import com.example.network.external.StudiesService
import com.example.network.internal.service.AwardServiceImpl
import com.example.network.internal.service.CategoryServiceImpl
import com.example.network.internal.service.CollectionServiceImpl
import com.example.network.internal.service.CommentServiceImpl
import com.example.network.internal.service.MovieServiceImpl
import com.example.network.internal.service.PersonServiceImpl
import com.example.network.internal.service.SeasonServiceImpl
import com.example.network.internal.service.StudiesServiceImpl
import dagger.Binds
import dagger.Module

@Module
interface ServiceModule {
    @Binds
    fun bindsAwardServiceImpl(service: AwardServiceImpl): AwardService

    @Binds
    fun bindsCategoryServiceImpl(service: CategoryServiceImpl): CategoryService

    @Binds
    fun bindsCollectionServiceImpl(service: CollectionServiceImpl): CollectionService

    @Binds
    fun bindsCommentServiceImpl(service: CommentServiceImpl): CommentService

    @Binds
    fun bindsMovieServiceImpl(service: MovieServiceImpl): MovieService

    @Binds
    fun bindsPersonServiceImpl(service: PersonServiceImpl): PersonService

    @Binds
    fun bindsSeasonServiceImpl(service: SeasonServiceImpl): SeasonService

    @Binds
    fun bindsStudiesServiceImpl(service: StudiesServiceImpl): StudiesService
}