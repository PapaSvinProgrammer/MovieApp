package com.mordva.security.internal.di

import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.SecurityRepositoryImpl
import com.mordva.util.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
internal interface SecurityModuleImpl {
    @ApplicationScope
    @Binds
    fun bindsSecurityRepositoryImpl(repository: SecurityRepositoryImpl): SecurityRepository
}
