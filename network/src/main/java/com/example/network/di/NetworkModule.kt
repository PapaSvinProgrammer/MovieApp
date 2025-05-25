package com.example.network.di

import com.example.network.KtorClient
import dagger.Module
import dagger.Provides
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


        @Provides
        @Singleton
        fun provideKtorClient(okHttpClient: OkHttpClient): KtorClient {
            return KtorClient(okHttpClient)
        }
    }
}