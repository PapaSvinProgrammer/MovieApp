package com.example.network.external.di

import com.example.network.internal.di.NetworkModuleImpl
import dagger.Module

@Module(
    includes = [NetworkModuleImpl::class]
)
interface NetworkModule