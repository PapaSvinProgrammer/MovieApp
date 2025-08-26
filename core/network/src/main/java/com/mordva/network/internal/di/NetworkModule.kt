package com.mordva.network.internal.di

import dagger.Module

@Module(
    includes = [NetworkModuleImpl::class]
)
interface NetworkModule