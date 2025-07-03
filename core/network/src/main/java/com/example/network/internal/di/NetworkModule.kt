package com.example.network.internal.di

import com.example.network.external.AwardService
import com.example.network.external.CategoryService
import com.example.network.external.CollectionService
import com.example.network.external.CommentService
import com.example.network.external.MovieService
import com.example.network.external.PersonService
import com.example.network.external.SeasonService
import com.example.network.external.StudiesService
import com.example.network.internal.core.HttpClientFactory
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
import dagger.Provides
import io.ktor.client.HttpClient
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
interface NetworkModule {
    companion object {
        @Provides
        @Singleton
        fun provideOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Singleton
        @Provides
        fun provideHttpClient(okHttpClient: OkHttpClient): HttpClient {
            return HttpClientFactory.create(okHttpClient)
        }
    }
    
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