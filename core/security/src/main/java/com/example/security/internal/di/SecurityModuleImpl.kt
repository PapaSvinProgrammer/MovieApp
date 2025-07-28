package com.example.security.internal.di

import com.example.security.external.KeyStoreRepository
import com.example.security.internal.KeyStoreManager
import com.example.security.internal.KeyStoreRepositoryImpl
import com.example.utils.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface SecurityModuleImpl {
    @Binds
    @ApplicationScope
    fun bindsHeyStoreRepositoryImpl(repository: KeyStoreRepositoryImpl): KeyStoreRepository

    companion object Companion {
        @Provides
        @ApplicationScope
        fun providesKeyStoreManager(): KeyStoreManager {
            return KeyStoreManager()
        }
    }
}
