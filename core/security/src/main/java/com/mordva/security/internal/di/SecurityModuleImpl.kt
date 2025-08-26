package com.mordva.security.internal.di

import com.mordva.security.external.KeyStoreRepository
import com.mordva.security.internal.KeyStoreManager
import com.mordva.security.internal.KeyStoreRepositoryImpl
import com.mordva.util.ApplicationScope
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
