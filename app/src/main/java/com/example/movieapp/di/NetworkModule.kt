package com.example.movieapp.di

import com.example.network.KtorClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface NetworkModule {
    companion object {
        @Singleton
        @Provides
        fun provideKtorClient(): KtorClient {
            return KtorClient()
        }
    }
}