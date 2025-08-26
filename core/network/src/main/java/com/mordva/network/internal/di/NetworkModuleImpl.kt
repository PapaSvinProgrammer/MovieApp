package com.mordva.network.internal.di

import com.mordva.network.external.AwardService
import com.mordva.network.external.CategoryService
import com.mordva.network.external.CollectionService
import com.mordva.network.external.CommentService
import com.mordva.network.external.MovieService
import com.mordva.network.external.PersonService
import com.mordva.network.external.SeasonService
import com.mordva.network.external.StudiesService
import com.mordva.network.internal.core.HttpClientFactory
import com.mordva.network.internal.service.AwardServiceImpl
import com.mordva.network.internal.service.CategoryServiceImpl
import com.mordva.network.internal.service.CollectionServiceImpl
import com.mordva.network.internal.service.CommentServiceImpl
import com.mordva.network.internal.service.MovieServiceImpl
import com.mordva.network.internal.service.PersonServiceImpl
import com.mordva.network.internal.service.SeasonServiceImpl
import com.mordva.network.internal.service.StudiesServiceImpl
import com.mordva.util.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
internal interface NetworkModuleImpl {
    @Binds
    @ApplicationScope
    fun bindsAwardServiceImpl(service: AwardServiceImpl): AwardService

    @Binds
    @ApplicationScope
    fun bindsCategoryServiceImpl(service: CategoryServiceImpl): CategoryService

    @Binds
    @ApplicationScope
    fun bindsCollectionServiceImpl(service: CollectionServiceImpl): CollectionService

    @Binds
    @ApplicationScope
    fun bindsCommentServiceImpl(service: CommentServiceImpl): CommentService

    @Binds
    @ApplicationScope
    fun bindsMovieServiceImpl(service: MovieServiceImpl): MovieService

    @Binds
    @ApplicationScope
    fun bindsPersonServiceImpl(service: PersonServiceImpl): PersonService

    @Binds
    @ApplicationScope
    fun bindsSeasonServiceImpl(service: SeasonServiceImpl): SeasonService

    @Binds
    @ApplicationScope
    fun bindsStudiesServiceImpl(service: StudiesServiceImpl): StudiesService

    companion object {
        @Provides
        @ApplicationScope
        fun provideOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @ApplicationScope
        fun provideHttpClient(okHttpClient: OkHttpClient): HttpClient {
            return HttpClientFactory.create(okHttpClient)
        }
    }
}