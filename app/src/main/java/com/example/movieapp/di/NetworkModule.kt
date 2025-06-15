package com.example.movieapp.di

import com.example.network.core.HttpClientFactory
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
}